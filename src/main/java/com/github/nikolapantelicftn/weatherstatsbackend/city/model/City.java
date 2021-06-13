package com.github.nikolapantelicftn.weatherstatsbackend.city.model;

import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.DayReport;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.Temperature;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String countryCode;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DayReport> dayReports = new ArrayList<>();

    protected City() { }

    public City(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public City(Long id, String name, String countryCode) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
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

    public List<DayReport> getDayReports() {
        return dayReports;
    }

    public void clearDayReports() {
        dayReports = new ArrayList<>();
    }

    public Temperature getAverage() {
        double average = this.dayReports.stream().mapToDouble(report -> report.getAverage().getValue()).average().orElse(0);
        return new Temperature(average);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) && Objects.equals(name, city.name)
                && dayReports.containsAll(city.dayReports) && city.dayReports.containsAll(dayReports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dayReports);
    }

}
