package com.example.Clima.exception;

public class TemperatureOutOfRangeException extends RuntimeException {
    public TemperatureOutOfRangeException(String message) {
        super(message);
    }
}
