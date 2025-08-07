package com.e_commerce.project.exceptions;

import com.e_commerce.project.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGobalExceptionHandler {
// This class handles global exceptions for the application
    @ExceptionHandler(MethodArgumentNotValidException.class)// This method handles validation exceptions
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err ->{
            String fieldName = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldName, message);
        });
        return  new ResponseEntity<Map<String, String>>(response,
                HttpStatus.BAD_REQUEST);
    }// This method processes the exception and returns a map of field names and error messages

    @ExceptionHandler(ResourceNotFoundException.class)// This method handles resource not found exceptions
    public ResponseEntity<APIResponse> myResourceNoFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIExceptions.class)// This method handles resource not found exceptions
    public ResponseEntity<APIResponse> myAPIExceptions(APIExceptions e){
        String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
