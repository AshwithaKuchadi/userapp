package com.example.WeatherApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WeatherDataStorage {
   
    private static final String DB_URL = "jdbc:mysql://localhost:3306/weather_db"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "root123"; 

   
    public static void storeWeatherData(String city, double temperature, double feelsLike, String weatherCondition, String description) {
        // Check if the data already exists
        if (dataExists(city)) {
            System.out.println("Weather data for " + city + " already exists. Skipping insertion.");
            return; // Exit if data already exists
        }

        String query = "INSERT INTO weather_data (city, temperature, feels_like, weather_condition, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city);
            stmt.setDouble(2, temperature);
            stmt.setDouble(3, feelsLike);
            stmt.setString(4, weatherCondition);
            stmt.setString(5, description);

            stmt.executeUpdate();
            System.out.println("Weather data stored successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to store weather data.");
        }
    }

  
    private static boolean dataExists(String city) {
        String query = "SELECT COUNT(*) FROM weather_data WHERE city = ? AND timestamp >= NOW() - INTERVAL 1 HOUR"; // Adjust time interval as needed

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
}

