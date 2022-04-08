package com.example.library.exceptions;

public class NotAllowedException extends RuntimeException{
    public NotAllowedException(String message) {
        super(message);
    }
}
