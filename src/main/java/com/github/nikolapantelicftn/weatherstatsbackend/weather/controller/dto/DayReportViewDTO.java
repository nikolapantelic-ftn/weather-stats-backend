package com.github.nikolapantelicftn.weatherstatsbackend.weather.controller.dto;

import com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto.CityViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.Temperature;

import java.time.LocalDate;
import java.util.List;

public class DayReportViewDTO {

    private Long id;
    private CityViewDTO city;
    private LocalDate date;
    private Temperature average;
    private List<HourReportViewDTO> hourReports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityViewDTO getCity() {
        return city;
    }

    public void setCity(CityViewDTO city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Temperature getAverage() {
        return average;
    }

    public void setAverage(Temperature average) {
        this.average = average;
    }

    public List<HourReportViewDTO> getHourReports() {
        return hourReports;
    }

    public void setHourReports(List<HourReportViewDTO> hourReports) {
        this.hourReports = hourReports;
    }

}
