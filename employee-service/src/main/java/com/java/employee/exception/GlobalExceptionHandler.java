package com.java.employee.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                        WebRequest request) {
        var errorDetails = new ErrorDetails(
                now(),
                ex.getMessage(),
                request.getDescription(false),
                "NOT_FOUND"
        );

        return ResponseEntity.status(NOT_FOUND)
                             .body(errorDetails);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex,
                                                                          WebRequest request) {
        var errorDetails = new ErrorDetails(
                now(),
                ex.getMessage(),
                request.getDescription(false),
                "EMAIL_ALREADY_EXISTS"
        );

        return ResponseEntity.status(CONFLICT)
                             .body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex,
                                                              WebRequest request) {
        var errorDetails = new ErrorDetails(
                now(),
                ex.getMessage(),
                request.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                             .body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        var errorsMap = new HashMap<String, String>();
        var errorList = ex.getBindingResult()
                          .getAllErrors();
        errorList.forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var message = error.getDefaultMessage();
            errorsMap.put(fieldName, message);
        });

        return ResponseEntity.status(BAD_REQUEST)
                             .body(errorsMap);
    }
}
