package com.example.WeatherApp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtility {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/weather_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "root123"; 

    public static void saveWeatherData(WeatherResponse weatherResponse, String city) {
        String insertSQL = "INSERT INTO weather_data (city, temperature, feels_like, weather_condition, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, city);
            pstmt.setDouble(2, weatherResponse.getMain().getTemp());
            pstmt.setDouble(3, weatherResponse.getMain().getFeelsLike());
            pstmt.setString(4, weatherResponse.getWeather()[0].getMain());
            pstmt.setString(5, weatherResponse.getWeather()[0].getDescription());
            pstmt.executeUpdate();
            System.out.println("Weather data saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
