package com.cbccafe.exceptionHandler;

import com.cbccafe.payload.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    ResponseMessage responseMessage;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleValidationExceptions(MethodArgumentNotValidException ex){

        ex.getBindingResult().getFieldErrors().forEach(error ->
                responseMessage = new ResponseMessage(error.getDefaultMessage())
        );
        return new  ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
