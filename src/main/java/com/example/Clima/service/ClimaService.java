package com.example.Clima.service;

import java.util.List;

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

    public List<Clima> getAllWeather() {
        return climaRepository.findAll();
    }

    public Clima addWeather(Clima clima) {
        validateWeather(clima);

        return climaRepository.save(clima);
    }

    public Clima updateWeather(Long id, Clima clima) {
        Clima existingWeather = climaRepository.findById(id)
                .orElseThrow(() -> new ClimaNotFoundException("Weather data not found for id: " + id));
        
        validateWeather(clima);

        existingWeather.setCity(clima.getCity());
        existingWeather.setDate(clima.getDate());
        existingWeather.setDescription(clima.getDescription());
        existingWeather.setTemperature(clima.getTemperature());

        return climaRepository.save(existingWeather);
    }

    public void deleteWeather(Long id) {
        Clima existingWeather = climaRepository.findById(id)
                .orElseThrow(() -> new ClimaNotFoundException("Weather data not found for id: " + id));
        climaRepository.delete(existingWeather);
    }

    private void validateWeather(Clima clima) {
        if (clima.getTemperature() < -50 || clima.getTemperature() > 50) {
            throw new TemperatureOutOfRangeException("Temperature out of range: " + clima.getTemperature());
        }

        if (clima.getCity() == null || clima.getCity().isEmpty()) {
            throw new CityNotFoundException("City name is missing");
        }
    }

    private boolean isValidDate(String date) {
        // Simple validation for date format YYYY-MM-DD
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
