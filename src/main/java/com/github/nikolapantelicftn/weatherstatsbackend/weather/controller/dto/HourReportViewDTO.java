package com.github.nikolapantelicftn.weatherstatsbackend.weather.controller.dto;

import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.Temperature;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HourReportViewDTO that = (HourReportViewDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(time, that.time)
                && Objects.equals(temperature, that.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, temperature);
    }

}
