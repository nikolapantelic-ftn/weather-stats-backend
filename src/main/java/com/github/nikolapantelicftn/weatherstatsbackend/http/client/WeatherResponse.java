package com.github.nikolapantelicftn.weatherstatsbackend.http.client;

import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.DayReport;

import java.util.List;

public class WeatherResponse {

    private List<DayReport> weatherReport;

    public WeatherResponse(List<DayReport> weatherReport) {
        this.weatherReport = weatherReport;
    }

    public List<DayReport> getWeatherReport() {
        return weatherReport;
    }

    public void setWeatherReport(List<DayReport> weatherReport) {
        this.weatherReport = weatherReport;
    }

}
