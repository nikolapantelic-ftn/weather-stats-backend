package com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto;

import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.Temperature;

import java.util.List;

public class CityViewDTO {

    private Long id;
    private String name;
    private Temperature average;
    private List<CityDayReportDTO> dayReports;

    protected CityViewDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Temperature getAverage() {
        return average;
    }

    public void setAverage(Temperature average) {
        this.average = average;
    }

    public List<CityDayReportDTO> getDayReports() {
        return dayReports;
    }

    public void setDayReports(List<CityDayReportDTO> dayReports) {
        this.dayReports = dayReports;
    }

}
