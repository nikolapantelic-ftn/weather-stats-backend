package com.github.nikolapantelicftn.weatherstatsbackend.weather.controller.dto;

import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.Temperature;

import java.time.LocalDateTime;

public class HourReportViewDTO {

    private Long id;
    private LocalDateTime time;
    private Temperature temperature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

}
