package com.sunshine.weatherapi.models;

public class ErrorData extends WeatherData {

    public String errorDescription;

    public ErrorData(String providerName, String errorDescription) {
        super(providerName);
        this.errorDescription = errorDescription;
    }
}
