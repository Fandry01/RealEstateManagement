package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Exceptions.BadRequestException;
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
    public ResponseEntity<List<String>> exceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(this::getErrorMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }
    private String getErrorMessage(FieldError fieldError) {
        String fieldName = fieldError.getField();
        String errorMessage = fieldError.getDefaultMessage();
        if (fieldName.equals("type") && errorMessage.equals("must be one of APPARTEMENT, RIJTJESHUIS, VRIJSTAANDEHUIS, PENTHOUSE, TWEEONDEREENKAP")) {
            return "Invalid value for " + fieldName + ": " + errorMessage;
        }
        return fieldName + " " + errorMessage;
    }
}


