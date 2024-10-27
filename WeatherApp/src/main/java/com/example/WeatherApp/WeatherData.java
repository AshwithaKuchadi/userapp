package com.example.WeatherApp;


import java.time.LocalDateTime;

public class WeatherData {
    private double temperature;
    private double feelsLike;
    private double humidity;
    private LocalDateTime timestamp;

    public WeatherData(double temperature, double feelsLike, double humidity) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public double getTemperature() { return temperature; }
    public double getFeelsLike() { return feelsLike; }
    public double getHumidity() { return humidity; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
