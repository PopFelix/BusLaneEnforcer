package ro.ubb.thesis.app.config.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class IncorrectPasswordException extends AuthenticationException {

    public IncorrectPasswordException(String t) {
        super(t);
    }

}
