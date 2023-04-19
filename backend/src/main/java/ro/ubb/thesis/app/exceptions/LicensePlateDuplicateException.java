package ro.ubb.thesis.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LicensePlateDuplicateException extends RuntimeException{
    public LicensePlateDuplicateException(String message) {
        super(message);
    }
}
