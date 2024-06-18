package com.example.Clima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Clima.entity.Clima;
import com.example.Clima.exception.CityNotFoundException;
import com.example.Clima.exception.ClimaNotFoundException;
import com.example.Clima.exception.InvalidDateException;
import com.example.Clima.exception.TemperatureOutOfRangeException;
import com.example.Clima.repository.ClimaRepository;

@Service
public class ClimaService {
     @Autowired
    private ClimaRepository climaRepository;

    public Clima getWeather(String city, String date) {
        if (!isValidDate(date)) {
            throw new InvalidDateException("Invalid date format: " + date);
        }

        return climaRepository.findByCityAndDate(city, date)
                .orElseThrow(() -> new ClimaNotFoundException("Weather data not found for city: " + city + " on date: " + date));
    }

    public Clima addWeather(Clima clima) {
        if (clima.getTemperature() < -50 || clima.getTemperature() > 50) {
            throw new TemperatureOutOfRangeException("Temperature out of range: " + clima.getTemperature());
        }

        if (clima.getCity() == null || clima.getCity().isEmpty()) {
            throw new CityNotFoundException("City name is missing");
        }

        return climaRepository.save(clima);
    }

    private boolean isValidDate(String date) {
        // Simple validation for date format YYYY-MM-DD
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
