package com.example.Clima.entity;

public class Clima {
    private Long id;
    private String city;
    private String date;
    private String description;
    private double temperature;

    public Clima(Long id, String city, String date, String description, double temperature) {
        this.id = id;
        this.city = city;
        this.date = date;
        this.description = description;
        this.temperature = temperature;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
