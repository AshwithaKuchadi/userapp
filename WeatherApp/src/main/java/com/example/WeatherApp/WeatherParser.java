package com.example.WeatherApp;

import com.google.gson.Gson;

public class WeatherParser {

    public static void parseWeatherData(String jsonData) {
        if (jsonData == null || jsonData.isEmpty()) {
            System.out.println("No data to parse.");
            return;
        }

        Gson gson = new Gson();
        WeatherResponse weatherResponse = gson.fromJson(jsonData, WeatherResponse.class);

        if (weatherResponse != null && weatherResponse.getMain() != null && weatherResponse.getWeather() != null) {
            double tempInKelvin = weatherResponse.getMain().getTemp();
            double tempInCelsius = tempInKelvin - 273.15; // Convert Kelvin to Celsius
            System.out.printf("Temperature in Celsius: %.2f °C%n", tempInCelsius);

            String weatherCondition = weatherResponse.getWeather()[0].getMain();
            System.out.println("Weather Condition: " + weatherCondition);

            long timestamp = weatherResponse.getDt();
            System.out.println("Data Timestamp: " + timestamp);
        } else {
            System.out.println("Unable to parse weather data.");
        }
    }
}
