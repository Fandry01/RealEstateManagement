package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Exceptions.BadRequestException;
import com.example.realestatemanagment.Exceptions.HttpMessageNotReadableException;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> exception( UsernameNotFoundException exception){
        return  new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception (BadRequestException exception){
        return  new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity <List<String>> exception(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(exception.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage() ).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> exception (HttpMessageNotReadableException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

}


