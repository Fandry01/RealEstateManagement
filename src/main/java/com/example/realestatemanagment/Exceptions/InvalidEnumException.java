package com.example.realestatemanagment.Exceptions;

public class InvalidEnumException extends RuntimeException{
    private static final long serialVersionUID = 1l;
    public InvalidEnumException(){
        super();
    }

    public InvalidEnumException(String message){
        super("Please choose the correct type");
    }
}
