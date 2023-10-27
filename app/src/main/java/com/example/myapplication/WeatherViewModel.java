package com.example.myapplication;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class WeatherViewModel extends ViewModel {
    private WeatherRepository repository;

    // LiveData to observe temperature data

    private MutableLiveData<String> nameLiveData = new MutableLiveData<>();
    private MutableLiveData<String> celsiusLiveData = new MutableLiveData<>();
    private MutableLiveData<String> lastUpdatedLiveData = new MutableLiveData<>();
    private MutableLiveData<String> fahrenheitLiveData = new MutableLiveData<>();

    public WeatherViewModel() {
        // Initialize the repository (use dependency injection here)
        repository = new WeatherRepository();
    }

    public LiveData<String> getNameLiveData() {
        return nameLiveData;
    }
    public LiveData<String> getLastUpdatedLiveData() {
        return lastUpdatedLiveData;
    }
    public LiveData<String> getCelsiusLiveData() {
        return celsiusLiveData;
    }
    public LiveData<String> getFahrenheitLiveData() {
        return fahrenheitLiveData;
    }

    // Method to fetch weather data
    public void fetchWeatherData(String apiKey, String city) {
        // Use the repository to make the API request
        repository.fetchWeather(apiKey, city, new WeatherRepository.OnWeatherDataFetchedListener() {
            @Override
            public void onWeatherDataFetched(double celsius, double fahrenheit, String name, String last_updated) {
                nameLiveData.postValue("Name: " + name);
                lastUpdatedLiveData.postValue("Last Updated: " + last_updated);
                celsiusLiveData.postValue("Celsius: " + celsius);
                fahrenheitLiveData.postValue("Fahrenheit: " + fahrenheit);
            }

            @Override
            public void onError(String errorMessage) {
                // Handle errors here
                Log.e("WeatherViewModel", "API Error: " + errorMessage);
            }
        });
    }
}
