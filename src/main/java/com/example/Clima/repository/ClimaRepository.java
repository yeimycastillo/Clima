package com.example.Clima.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.Clima.entity.Clima;

import jakarta.annotation.PostConstruct;

@Repository
public class ClimaRepository {
    private List<Clima> weatherData = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    public void initData() {
        weatherData.add(new Clima(1L, "New York", "2024-06-17", "Sunny", 25.0));
        weatherData.add(new Clima(2L, "London", "2024-06-17", "Cloudy", 18.0));
        weatherData.add(new Clima(3L, "Tokyo", "2024-06-17", "Rainy", 22.0));
    }

    public Optional<Clima> findByCityAndDate(String city, String date) {
        return weatherData.stream()
                .filter(weather -> weather.getCity().equalsIgnoreCase(city) && weather.getDate().equals(date))
                .findFirst();
    }

    public List<Clima> findAll() {
        return new ArrayList<>(weatherData);
    }

    public Clima save(Clima clima) {
        if (clima.getId() == null) {
            clima.setId(idCounter.incrementAndGet());
            weatherData.add(clima);
        } else {
            Optional<Clima> existingWeatherOpt = findById(clima.getId());
            if (existingWeatherOpt.isPresent()) {
                Clima existingWeather = existingWeatherOpt.get();
                existingWeather.setCity(clima.getCity());
                existingWeather.setDate(clima.getDate());
                existingWeather.setDescription(clima.getDescription());
                existingWeather.setTemperature(clima.getTemperature());
            } else {
                weatherData.add(clima);
            }
        }
        return clima;
    }

    public Optional<Clima> findById(Long id) {
        return weatherData.stream()
                .filter(weather -> weather.getId().equals(id))
                .findFirst();
    }

    public void delete(Clima clima) {
        weatherData.remove(clima);
    }
}
