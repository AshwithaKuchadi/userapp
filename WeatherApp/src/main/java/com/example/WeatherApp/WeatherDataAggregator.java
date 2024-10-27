package com.example.WeatherApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WeatherDataAggregator {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/weather_db"; // Update with your DB URL
    private static final String USER = "root"; // Update with your DB username
    private static final String PASSWORD = "root123"; // Update with your DB password

    public static double getAverageTemperature(String city) {
        String query = "SELECT AVG(temperature) as avg_temp FROM weather_data WHERE city = ? AND timestamp >= NOW() - INTERVAL 1 DAY";
        double averageTemp = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                averageTemp = rs.getDouble("avg_temp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return averageTemp;
    }

    public static double getWeeklyAverageTemperature(String city) {
        String query = "SELECT AVG(temperature) as weekly_avg_temp FROM weather_data WHERE city = ? AND timestamp >= NOW() - INTERVAL 7 DAY";
        double weeklyAvgTemp = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                weeklyAvgTemp = rs.getDouble("weekly_avg_temp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weeklyAvgTemp;
    }
}
