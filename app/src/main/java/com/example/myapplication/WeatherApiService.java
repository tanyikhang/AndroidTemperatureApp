package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("current.json")
    Call<WeatherResponse> getWeatherData(
            @Query("key") String apiKey,
            @Query("q") String city
    );
}

