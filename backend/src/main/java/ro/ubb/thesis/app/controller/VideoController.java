package ro.ubb.thesis.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.thesis.api.VideoApi;
import ro.ubb.thesis.api.dto.ResponseFileDto;
import ro.ubb.thesis.app.exceptions.ResponseMessage;
import ro.ubb.thesis.app.service.UnauthorizedVehicleLogService;
import ro.ubb.thesis.app.service.VideoUploadService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VideoController implements VideoApi {
    private final VideoUploadService videoUploadService;
    private final UnauthorizedVehicleLogService logService;

    @Override
    public ResponseEntity<ResponseMessage> uploadVideo(MultipartFile file) {
        String message = "";
        try {
            videoUploadService.uploadVideoFile(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @Override
    public ResponseEntity<List<ResponseFileDto>> getAllVideosForLicensePlate(String licensePlate) {
        return ResponseEntity.ok(logService.getAllVideoUploadsForLicensePlate(licensePlate));
    }

    @Override
    public ResponseEntity<byte[]> getFile(String id) {
        var videoUpload = videoUploadService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + videoUpload.getName() + "\"")
                .body(videoUpload.getData());

    }
}
