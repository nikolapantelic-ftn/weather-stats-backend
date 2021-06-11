package com.github.nikolapantelicftn.weatherstatsbackend.temperature.model;

import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DayReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private City city;
    private LocalDate date;
    @Embedded
    private Temperature average;
    @OneToMany(mappedBy = "day_reports")
    private List<HourReport> hourReports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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

    public List<HourReport> getHourReports() {
        return hourReports;
    }

    public void setHourReports(List<HourReport> hourReports) {
        this.hourReports = hourReports;
    }

}
