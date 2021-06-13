package com.github.nikolapantelicftn.weatherstatsbackend.weather.model;

import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DayReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @JoinColumn(name = "city_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private City city;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HourReport> hourReports;

    public DayReport(LocalDate date, List<HourReport> hourReports) {
        this.date = date;
        this.hourReports = hourReports;
    }

    public DayReport(Long id, LocalDate date, List<HourReport> hourReports) {
        this.id = id;
        this.date = date;
        this.hourReports = hourReports;
    }

    protected DayReport() {}

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Temperature getAverage() {
        double avg = hourReports.stream().
                mapToDouble(report -> report.getTemperature().getValue())
                .average().orElse(0);
        return new Temperature(avg);
    }

    public List<HourReport> getHourReports() {
        return hourReports;
    }

}
