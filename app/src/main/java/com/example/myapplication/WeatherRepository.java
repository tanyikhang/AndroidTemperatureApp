package com.example.myapplication;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {

    public void fetchWeather(String apiKey, String city, OnWeatherDataFetchedListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.weatherapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiService apiService = retrofit.create(WeatherApiService.class);

        Call<WeatherResponse> call = apiService.getWeatherData(apiKey, city);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("API Request", "URL: " + call.request().url().toString());
                    WeatherResponse weatherResponse = response.body();
                    Current current = weatherResponse.getCurrent();
                    Location location = weatherResponse.getLocation();
                    String name = location.getName();
                    String last_updated = current.getLastUpdated();
                    double celsius = current.getTempCelsius();
                    double fahrenheit = current.getTempFahrenheit();


                    listener.onWeatherDataFetched(celsius, fahrenheit, name, last_updated);
                } else {
                    listener.onError("API request failed"); // Handle API error

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                listener.onError("Network request failed"); // Handle network error
            }
        });
    }

    public interface OnWeatherDataFetchedListener {
        void onWeatherDataFetched(double celsius, double fahrenheit, String name, String last_updated);
        void onError(String errorMessage);
    }
}
