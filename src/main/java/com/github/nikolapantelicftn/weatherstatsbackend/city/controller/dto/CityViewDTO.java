package com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto;

import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.Temperature;

import java.util.List;
import java.util.Objects;

public class CityViewDTO {

    private Long id;
    private String name;
    private String countryCode;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityViewDTO that = (CityViewDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(average, that.average) && dayReports.containsAll(that.dayReports)
                && that.dayReports.containsAll(dayReports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, average, dayReports);
    }

}
