package epam.com.esm.exception;

import epam.com.esm.exception.types.EntityAlreadyExists;
import epam.com.esm.exception.types.EntityNotFoundException;
import epam.com.esm.exception.types.InputException;
import epam.com.esm.exception.types.OperationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDto> accessDenied() {
        String errorMessage = "Access Denied";
        String errorCode = HttpStatus.UNAUTHORIZED.value() + "01";
        ErrorDto erDto = new ErrorDto(errorMessage, errorCode);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> entityNotFound(UsernameNotFoundException e) {
        String errorMessage = e.getMessage();
        String errorCode = HttpStatus.NOT_FOUND.value() + "01";
        ErrorDto erDto = new ErrorDto(errorMessage, errorCode);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erDto);
    }

    @ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<ErrorDto> entityAlreadyExists(EntityAlreadyExists e) {
        String errorMessage = e.getMessage();
        String errorCode = HttpStatus.BAD_REQUEST.value() + "01";
        ErrorDto erDto = new ErrorDto(errorMessage, errorCode);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erDto);
    }

    @ExceptionHandler(InputException.class)
    public ResponseEntity<ErrorDto> entityAlreadyExists(InputException e) {
        String errorMessage = e.getMessage();
        String errorCode = HttpStatus.BAD_REQUEST.value() + "02";
        ErrorDto erDto = new ErrorDto(errorMessage, errorCode);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erDto);
    }

    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<ErrorDto> entityAlreadyExists(OperationFailedException e) {
        String errorMessage = e.getMessage();
        String errorCode = HttpStatus.CONFLICT.value() + "01";
        ErrorDto erDto = new ErrorDto(errorMessage, errorCode);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erDto);
    }
}