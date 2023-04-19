package ro.ubb.thesis.app.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ro.ubb.thesis.app.config.security.exceptions.IncorrectPasswordException;
import ro.ubb.thesis.app.config.security.exceptions.RefreshTokenExpiredException;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {VehicleNotFoundException.class, VideoNotFoundException.class})
    public ResponseMessage handleNotFoundException(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseMessage(exception.getMessage());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {
            LicensePlateDuplicateException.class})
    public ResponseMessage handleAlreadyExistsException(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseMessage(exception.getMessage());
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {RefreshTokenExpiredException.class,
            IncorrectPasswordException.class,
            UsernameNotFoundException.class})
    public ResponseMessage handleUnauthorizedException(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseMessage("Unauthorized!");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseMessage handleForbiddenException(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseMessage("Forbidden!");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            SignatureException.class})
    public ResponseMessage handleBadRequestExceptionMessage(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseMessage("Bad request!" + exception.getMessage());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ResponseMessage handleInternalServerException(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseMessage("Internal Server Error!");
    }
}
