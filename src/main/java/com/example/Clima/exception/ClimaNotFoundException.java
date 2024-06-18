package com.example.Clima.exception;

public class ClimaNotFoundException  extends RuntimeException {
    public ClimaNotFoundException(String message) {
        super(message);
    }
}
