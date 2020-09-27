package com.sunshine.weatherapi.resources;

import com.sunshine.weatherapi.models.WeatherData;
import com.sunshine.weatherapi.services.WeatherFetchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherResource {

    @Autowired
    WeatherFetchingService weatherFetchingService;

    @RequestMapping("/{city}/{providerId}")
    public List<WeatherData> getWeatherData(@PathVariable("city") String city, @PathVariable(value = "providerId", required = false) String providerId) {
        return weatherFetchingService.fetch(providerId, city);
    }

}
