package com.sunshine.weatherapi.models;

public class AccuWeatherData extends WeatherData {

    public AccuWeatherData() {
        super("AccuWeather");
    }

    public String LocalObservationDateTime;
    public long EpochTime;
    public String WeatherText;
    public int WeatherIcon;
    public boolean HasPrecipitation;
    public String PrecipitationType;
    public boolean IsDayTime;
    public TemperatureClass Temperature;
    public String MobileLink;
    public String Link;

    public static class TemperatureClass {
        public MetricClass Metric;
        public MetricClass Imperial;

        public static class MetricClass {
            public float Value;
            public String Unit;
            public int UnitType;
        }
    }
}
