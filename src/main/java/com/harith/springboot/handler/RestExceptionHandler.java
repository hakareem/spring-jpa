package com.harith.springboot.handler;

import com.harith.springboot.exception.ResourceNotFoundDetails;
import com.harith.springboot.exception.ResourceNotFoundException;
import com.harith.springboot.exception.ValidationExceptionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundException(ResourceNotFoundException exception) {
    return new ResponseEntity<>(
            ResourceNotFoundDetails.builder()
                    .title("Resource Not Found")
                    .timestamp(LocalDateTime.now())
                    .detail(exception.getMessage())
                    .status(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND
    );
}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
      log.info("Field error {}", exception.getBindingResult().getFieldErrors());

      List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
      String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
      String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Field Validation Error")
                        .timestamp(LocalDateTime.now())
                        .detail(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }


}
