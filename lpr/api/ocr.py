import glob
from collections import Counter

import keras_ocr


def character_recognition(path):
    pipeline = keras_ocr.pipeline.Pipeline()
    # Define a list of image paths or URLs
    image_paths = glob.glob(path)
    if len(image_paths) == 0:
        return None
    # Initialize a list to store the recognized texts
    recognized_texts = []

    # Process each image separately
    for image_path in image_paths:
        # Load the image
        image = keras_ocr.tools.read(image_path)

        # Perform OCR on the image
        predictions = pipeline.recognize([image])

        # Get the first set of predictions
        prediction = predictions[0]

        # Sort the predicted words from left to right
        sorted_prediction = sorted(prediction, key=lambda x: (x[1][1][0], x[1][0][0]))
        # Extract the recognized text from the prediction
        recognized_text = ''.join([word[0] for word in sorted_prediction])
        # Add the recognized text to the list
        recognized_texts.append(recognized_text)

    return Counter(text for text in recognized_texts if text != '').most_common(1)[0][0].upper()
