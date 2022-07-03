package com.example.SpringTest.exceptions;

import lombok.experimental.StandardException;

@StandardException
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
