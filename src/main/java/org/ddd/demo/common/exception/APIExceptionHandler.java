package org.ddd.demo.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Configuration class to define how spring will handle {@link TJFException}
 * classes.<br>
 * That classes have a defined status code for the response.
 */
@ControllerAdvice
public class APIExceptionHandler {

    /**
     * Default handler for {@link TJFException} exceptions.
     * 
     * @param {@link TJFException}
     * @return {@link ResponseEntity} with defined status code
     *         {@link HttpStatus#BAD_REQUEST} and {@link ErrorMessage} as body.
     */
    @ExceptionHandler({ IntegrityException.class })
    public ResponseEntity<ErrorMessage> handler(final IntegrityException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.from(ex));
    }
}