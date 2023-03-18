package platform.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CodeSharingControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CodeSharingPlatformException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCodeSharingPlatformException(CodeSharingPlatformException ex) {
        return ex.getMessage();
    }

}
