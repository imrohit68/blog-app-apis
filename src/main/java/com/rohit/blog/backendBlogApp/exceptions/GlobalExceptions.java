package com.rohit.blog.backendBlogApp.exceptions;

import com.rohit.blog.backendBlogApp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse= new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String,String>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//        Map<String,String> resp = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error)->{
//            String filedName = ((FieldError)error).getField();
//            String errorMessage = error.getDefaultMessage();
//            resp.put(filedName,errorMessage);
//        });
//        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
//    }
}
