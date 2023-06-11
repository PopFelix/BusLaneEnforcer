import json
import os
import shutil
import subprocess
from pathlib import Path
from typing import Annotated

import requests
from fastapi import FastAPI, UploadFile, Header
from starlette.middleware.cors import CORSMiddleware

from ocr import character_recognition

origins = [
    "http://localhost:4200",
    "http://localhost:8080",
]
app = FastAPI()
file_path = "../yolov5/"
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


def save_upload_file(upload_file: UploadFile, destination: Path) -> None:
    try:
        with destination.open("wb") as buffer:
            shutil.copyfileobj(upload_file.file, buffer)
    finally:
        upload_file.file.close()


@app.post("/scan/")
async def create_upload_file(upload_file: UploadFile, authorization: Annotated[str | None, Header()] = None):
    save_upload_file(upload_file, Path('temp', upload_file.filename))
    rel_path = f'../api/temp/{upload_file.filename}'
    out = subprocess.check_output(
        ['py', '../yolov5/detect.py'] + ['--weights', '../yolov5/runs/train/exp17/weights/best.pt', '--source',
                                         rel_path, '--save-crop'],
        stderr=subprocess.STDOUT)
    max_number = 0
    # Iterate over the directories in the specified directory
    for entry in os.scandir("../yolov5/runs/detect/"):
        if entry.is_dir() and entry.name.startswith("exp"):
            try:
                number = int(entry.name[3:])  # Extract the number from the directory name
                max_number = max(max_number, number)
            except ValueError:
                pass
    lpd_file_output = f'D:/Faculta/An 3/Licenta/BusLaneEnforcer/lpr/yolov5/runs/detect/exp{max_number}/crops/license-plate/*'
    print(lpd_file_output)
    plate_text = character_recognition(lpd_file_output)
    if plate_text is None:
        return {'message': "The scan did not find a suitable license plate."}
    headers = {"Authorization": authorization}
    files = {'file': open(rel_path, 'rb')}
    response = requests.post("http://localhost:8080/video/upload", files=files, headers=headers)
    if response.status_code == 200:
        unauthorized_log = {'licensePlateNumber': plate_text,
                            'videoUploadFileName': upload_file.filename}
        unauthorized_log_response = requests.post("http://localhost:8080/vehicle/unauthorized", headers=headers,
                                                  json=unauthorized_log)
        if unauthorized_log_response.status_code == 200:
            data = json.loads(unauthorized_log_response.text)
            if data['licensePlateNumber'] is None:
                return {'message': f"License plate number {plate_text} is accepted by the system."}
            return "flow with upload and unauth vehicle"
    return f"flow without upload status code {response.status_code}"


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="127.0.0.1", port=8000, log_level='debug', access_log=True)
