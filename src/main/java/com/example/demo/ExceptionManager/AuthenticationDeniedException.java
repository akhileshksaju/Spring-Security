package com.example.demo.ExceptionManager;

public class AuthenticationDeniedException extends Exception{

    public AuthenticationDeniedException(String message){
        super(message);
    }
}
