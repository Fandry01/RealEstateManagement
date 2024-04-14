package com.example.realestatemanagment.Exceptions;

public class HttpMessageNotReadableException extends RuntimeException{
    private static final long serialVersionUID = 1l;
    public HttpMessageNotReadableException(){
        super();
    }
    public HttpMessageNotReadableException(String message){
        super(message);
    }
}
