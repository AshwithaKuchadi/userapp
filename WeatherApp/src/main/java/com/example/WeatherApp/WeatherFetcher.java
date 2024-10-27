package com.example.WeatherApp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class WeatherFetcher {
    private static final String API_KEY = "ee26ea964cae8ae47f2d6b1963a25862";  

    public static String getWeatherData(String city) throws Exception {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status code is 200 (OK)
            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                System.out.println("Received JSON data: " + jsonResponse); // Log the received JSON data
                return jsonResponse;
            } else {
                System.out.println("Error: Unable to fetch data. Status code: " + response.statusCode());
                return null; // or throw an exception if preferred
            }
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
            throw e; // Rethrow the exception for further handling
        } catch (InterruptedException e) {
            System.out.println("Request was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw e;
        }
    }
}
