package com.InstaCio.exceptions;

import com.InstaCio.dtos.ApiResponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMsg> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        ApiResponseMsg status = ApiResponseMsg.builder().message(ex.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(status,HttpStatus.NOT_FOUND);
    }
}
