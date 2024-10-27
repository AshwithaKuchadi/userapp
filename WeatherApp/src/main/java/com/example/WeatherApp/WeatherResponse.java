package com.example.WeatherApp;

class Main {
    private double temp;
    private double feels_like;

    // Getters and Setters
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feels_like;
    }

    public void setFeelsLike(double feels_like) {
        this.feels_like = feels_like;
    }
}

class Weather {
    private String main;
    private String description;

    // Getters and Setters
    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

public class WeatherResponse {
    private Main main;
    private Weather[] weather;
    private long dt;

    // Getters and Setters
    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }
}
