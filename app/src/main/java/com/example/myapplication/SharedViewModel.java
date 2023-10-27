package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> apiKey = new MutableLiveData<>();
    private MutableLiveData<String> selectedCity = new MutableLiveData<>();

    public void setApiKey(String key) {
        apiKey.setValue(key);
    }

    public LiveData<String> getApiKey() {
        return apiKey;
    }

    public void setSelectedCity(String city) {
        selectedCity.setValue(city);
    }

    public LiveData<String> getSelectedCity() {
        return selectedCity;
    }
}