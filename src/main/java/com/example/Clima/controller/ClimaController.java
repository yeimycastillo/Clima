package com.example.Clima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<Clima> getWeather(@RequestParam(required = false) String city, @RequestParam(required = false) String date) {
        if(city != null && date != null){
            return List.of(climaService.getWeather(city, date));
        } else if (city != null){
            return climaService.getWeatherByCity(city);
        }
        else if (date != null) {
            return climaService.getWeatherByDate(date);
        }
        else{
            return climaService.getAllWeather();
        }
        
    }

    @GetMapping("/weathers")
    public List<Clima> getAllWeather() {
        return climaService.getAllWeather();
    }

    @PostMapping("/weather")
    public Clima addWeather(@RequestBody Clima clima) {
        return climaService.addWeather(clima);
    }

    @PutMapping("/weather/{id}")
    public Clima updateWeather(@PathVariable Long id, @RequestBody Clima clima) {
        return climaService.updateWeather(id, clima);
    }

    @DeleteMapping("/weather/{id}")
    public void deleteWeather(@PathVariable Long id) {
        climaService.deleteWeather(id);
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
