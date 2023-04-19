package ro.ubb.thesis.app.exceptions;

public class VideoNotFoundException extends RuntimeException{
    public VideoNotFoundException(String message) {
        super(message);
    }
}
