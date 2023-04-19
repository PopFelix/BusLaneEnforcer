package ro.ubb.thesis.app.config.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class RefreshTokenExpiredException extends AuthenticationException {
    public RefreshTokenExpiredException(String msg) {
        super(msg);
    }
}
