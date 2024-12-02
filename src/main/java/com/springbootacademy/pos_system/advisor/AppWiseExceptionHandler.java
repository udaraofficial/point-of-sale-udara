package com.springbootacademy.pos_system.advisor;

import com.springbootacademy.pos_system.exception.NotFoundException;
import com.springbootacademy.pos_system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWiseExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse>handleNotFoundException(NotFoundException e) {

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"No Data Found" , e.getMessage()),
                HttpStatus.NOT_FOUND
        );

    }
}
