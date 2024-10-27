package com.example.WeatherApp;

import java.util.Scanner;
import com.google.gson.Gson;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();

        try {
            
            String jsonData = WeatherFetcher.getWeatherData(city);
            // Parse JSON data into WeatherResponse object
            WeatherResponse weatherResponse = new Gson().fromJson(jsonData, WeatherResponse.class);

            
            if (weatherResponse != null && weatherResponse.getMain() != null && weatherResponse.getWeather() != null) {
                double tempCelsius = weatherResponse.getMain().getTemp() - 273.15; // Convert Kelvin to Celsius
                double feelsLikeCelsius = weatherResponse.getMain().getFeelsLike() - 273.15; // Convert Kelvin to Celsius

                System.out.printf("Current Temperature: %.2f °C%n", tempCelsius);
                System.out.printf("Feels Like: %.2f °C%n", feelsLikeCelsius);
                System.out.println("Weather Condition: " + weatherResponse.getWeather()[0].getMain());
                System.out.println("Description: " + weatherResponse.getWeather()[0].getDescription());

                // Store the data in the database
                WeatherDataStorage.storeWeatherData(city, tempCelsius, feelsLikeCelsius, 
                    weatherResponse.getWeather()[0].getMain(), weatherResponse.getWeather()[0].getDescription());

                // Get average temperature for the last 24 hours and last week
                double avgTempLastDay = WeatherDataAggregator.getAverageTemperature(city);
                double avgTempLastWeek = WeatherDataAggregator.getWeeklyAverageTemperature(city);

                System.out.printf("Average Temperature (Last 24 Hours): %.2f °C%n", avgTempLastDay);
                System.out.printf("Average Temperature (Last 7 Days): %.2f °C%n", avgTempLastWeek);
            } else {
                System.out.println("Unable to fetch weather data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
