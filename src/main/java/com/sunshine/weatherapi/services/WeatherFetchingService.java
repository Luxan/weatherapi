package com.sunshine.weatherapi.services;

import com.sunshine.weatherapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WeatherFetchingService {

    private enum ProviderChoice {
        openWeather,
        weatherBit,
        accuWeather,
        All
    }

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openWeatherApiKey}")
    private String openWeatherApiKey;
    @Value("${weatherBitApiKey}")
    private String weatherBitApiKey;
    @Value("${accuWeatherApiKey}")
    private String accuWeatherApiKey;

    private ProviderChoice getProviderChoice(String providerId) {
        ProviderChoice id = null;
        try {
            id = ProviderChoice.valueOf(providerId);
        } catch (IllegalArgumentException | NullPointerException e) {
            id = ProviderChoice.All;
        }
        return id;
    }

    public List<WeatherData> fetch(String providerId, String city) {
        ProviderChoice id = getProviderChoice(providerId);

        switch (id) {
            case openWeather:
                return Collections.singletonList(fetchOpenWeather(city));
            case weatherBit:
                return Collections.singletonList(fetchWeatherBit(city));
            case accuWeather:
                return Collections.singletonList(fetchAccuWeather(city));
            default:
                return Arrays.asList(fetchOpenWeather(city), fetchWeatherBit(city), fetchAccuWeather(city));
        }
    }

    public WeatherData fetchOpenWeather(String city) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, openWeatherApiKey);
        return restTemplate.getForObject(url, OpenWeatherData.class);
    }

    public WeatherData fetchWeatherBit(String city) {
        String url = String.format("https://api.weatherbit.io/v2.0/current?city=%s&key=%s", city, weatherBitApiKey);
        return restTemplate.getForObject(url, WeatherBitData.class);
    }

    public WeatherData fetchAccuWeather(String city) {
        String keyRequestUrl = String.format("https://dataservice.accuweather.com/locations/v1/search?q=%s&apikey=%s", city, accuWeatherApiKey);
        String responseContainingKey = restTemplate.getForObject(keyRequestUrl, String.class);
        String cityKey = retrieveCityKey(responseContainingKey, city);
        if (cityKey == null) {
            return new ErrorData("AccuWeather", "Cannot Find City key during fetching weather data from provider AccuWeather.");
        }
        String url = String.format("https://dataservice.accuweather.com/currentconditions/v1/%s?apikey=%s", cityKey, accuWeatherApiKey);
        AccuWeatherData[] data = restTemplate.getForObject(url, AccuWeatherData[].class);
        if (data != null) {
            if (data.length > 0) {
                return data[0];
            }
        }
        return new ErrorData("AccuWeather", "Received no data during fetching weather data from provider AccuWeather.");
    }

    private String retrieveCityKey(String responseContainingKey, String city) {
        try {
            String patternString = ".*\"Key\":\"(\\d+)\",\"Type\":\"City\",\"Rank\":\\d+,\"LocalizedName\":\"" + city + "\".*";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(responseContainingKey);
            matcher.find();
            return matcher.group(1);
        } catch (Exception e) {
            return null;
        }
    }
}
