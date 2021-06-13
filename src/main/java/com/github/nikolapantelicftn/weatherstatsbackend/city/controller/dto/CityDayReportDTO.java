package com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto;

import com.github.nikolapantelicftn.weatherstatsbackend.weather.controller.dto.HourReportViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.Temperature;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CityDayReportDTO {

    private Long id;
    private LocalDate date;
    private Temperature average;
    private List<HourReportViewDTO> hourReports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDayReportDTO that = (CityDayReportDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date)
                && Objects.equals(average, that.average) && hourReports.containsAll(that.hourReports)
                && that.hourReports.containsAll(hourReports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, average, hourReports);
    }

}
