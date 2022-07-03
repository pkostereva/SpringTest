package com.example.SpringTest.exceptions;

import lombok.experimental.StandardException;

@StandardException
public class TemperatureNotFoundException extends Exception{
    public TemperatureNotFoundException(String message) {
        super(message);
    }
}
