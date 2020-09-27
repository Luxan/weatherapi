package com.sunshine.weatherapi.models;

public class WeatherData {

    private String providerName;

    public WeatherData(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
