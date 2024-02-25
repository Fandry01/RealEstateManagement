package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
@ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception){
    return new ResponseEntity<>("this id is not in the database", HttpStatus.NOT_FOUND);
}
@ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> exception( UsernameNotFoundException exception){
    return  new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
}
}
