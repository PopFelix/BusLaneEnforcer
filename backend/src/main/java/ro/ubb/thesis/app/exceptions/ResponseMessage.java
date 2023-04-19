package ro.ubb.thesis.app.exceptions;

import java.util.List;

public class ResponseMessage {

    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

