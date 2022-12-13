package com.harith.springboot.handler;

import com.harith.springboot.exception.ResourceNotFoundDetails;
import com.harith.springboot.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundException(ResourceNotFoundException resource) {
    return new ResponseEntity<>(
            ResourceNotFoundDetails.builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.NOT_FOUND.value())
                    .title("Resource Not Found")
                    .detail(resource.getMessage())
                    .build(), HttpStatus.NOT_FOUND
    );
}

}
