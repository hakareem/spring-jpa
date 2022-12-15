package com.harith.springboot.handler;

import com.harith.springboot.exception.ExceptionDetails;
import com.harith.springboot.exception.ResourceNotFoundDetails;
import com.harith.springboot.exception.ResourceNotFoundException;
import com.harith.springboot.exception.ValidationExceptionDetails;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(
                ResourceNotFoundDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Resource not Found")
                        .detail(exception.getMessage())
                        .build(), HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Invalid Fields Error")
                        .detail(ex.getMessage())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .detail(ex.getMessage())
                .build();
        return new ResponseEntity(exceptionDetails, headers, status);
    }
}
