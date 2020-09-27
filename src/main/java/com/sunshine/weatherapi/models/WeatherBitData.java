package com.sunshine.weatherapi.models;

import java.util.List;

public class WeatherBitData extends WeatherData {
    public List<Data> data;
    public float count;

    public WeatherBitData() {
        super("WeatherBit");
    }

    public static class Data {
        public int rh;
        public String pod;
        public float lon;
        public float pres;
        public String timezone;
        public String ob_time;
        public String country_code;
        public int clouds;
        public long ts;
        public float solar_rad;
        public String state_code;
        public String city_name;
        public int wind_spd;
        public String wind_cdir_full;
        public String wind_cdir;
        public float slp;
        public float vis;
        public int h_angle;
        public String sunset;
        public float dni;
        public int dewpt;
        public int snow;
        public float uv;
        public float precip;
        public int wind_dir;
        public String sunrise;
        public float ghi;
        public float dhi;
        public float aqi;
        public float lat;
        public Weather weather;
        public String datetime;
        public float temp;
        public String station;
        public float elev_angle;
        public float app_temp;

        public static class Weather {
            public String icon;
            public int code;
            public String description;
        }
    }
}
