package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("temp_c")
    private double tempCelsius;

    @SerializedName("temp_f")
    private double tempFahrenheit;

    @SerializedName("last_updated")
    private String last_updated;

    public String getLastUpdated() { return last_updated; }

    public double getTempCelsius() {
        return tempCelsius;
    }

    public double getTempFahrenheit() {
        return tempFahrenheit;
    }

}