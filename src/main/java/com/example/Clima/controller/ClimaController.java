package com.example.Clima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Clima.entity.Clima;
import com.example.Clima.exception.CityNotFoundException;
import com.example.Clima.exception.ClimaNotFoundException;
import com.example.Clima.exception.InvalidDateException;
import com.example.Clima.exception.TemperatureOutOfRangeException;
import com.example.Clima.service.ClimaService;

@RestController
public class ClimaController {
     @Autowired
    private ClimaService climaService;

    @GetMapping("/weather")
    public Clima getWeather(@RequestParam String city, @RequestParam String date) {
        return climaService.getWeather(city, date);
    }

    @PostMapping("/weather")
    public Clima addWeather(@RequestBody Clima clima) {
        return climaService.addWeather(clima);
    }

    @ExceptionHandler(ClimaNotFoundException.class)
    public String handleWeatherNotFoundException(ClimaNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidDateException.class)
    public String handleInvalidDateException(InvalidDateException e) {
        return e.getMessage();
    }

    @ExceptionHandler(CityNotFoundException.class)
    public String handleCityNotFoundException(CityNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(TemperatureOutOfRangeException.class)
    public String handleTemperatureOutOfRangeException(TemperatureOutOfRangeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "An unexpected error occurred: " + e.getMessage();
    }
}
