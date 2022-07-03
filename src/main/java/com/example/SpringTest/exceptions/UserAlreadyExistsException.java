package com.example.SpringTest.exceptions;

import lombok.experimental.StandardException;

@StandardException
public class UserAlreadyExistsException extends  Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
