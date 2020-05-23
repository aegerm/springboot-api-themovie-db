package br.com.api.movies.resources.exceptions;

import br.com.api.movies.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * objectNotFound
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex) {
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDate.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}