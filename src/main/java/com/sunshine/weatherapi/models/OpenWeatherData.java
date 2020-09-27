package com.sunshine.weatherapi.models;

import java.util.List;

public class OpenWeatherData extends WeatherData {
    public Coord coord;
    public List<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public int dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;

    public OpenWeatherData() {
        super("OpenWeather");
    }

    public static class Coord {
        public float lon;
        public float lat;
    }

    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class Main {
        public float temp;
        public float feels_like;
        public float temp_min;
        public float temp_max;
        public int pressure;
        public int humidity;
    }

    public static class Wind {
        public float speed;
        public int deg;
    }

    public static class Clouds {
        public int all;
    }

    public static class Sys {
        public int type;
        public int id;
        public String country;
        public int sunrise;
        public int sunset;
    }
}
