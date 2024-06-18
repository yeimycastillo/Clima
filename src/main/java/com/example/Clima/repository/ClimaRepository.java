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

    public Clima save(Clima clima) {
        clima.setId(idCounter.incrementAndGet());
        weatherData.add(clima);
        return clima;
    }
}
