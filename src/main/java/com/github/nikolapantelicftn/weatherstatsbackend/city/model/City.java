package com.github.nikolapantelicftn.weatherstatsbackend.city.model;

import com.github.nikolapantelicftn.weatherstatsbackend.temperature.model.DayReport;
import com.github.nikolapantelicftn.weatherstatsbackend.temperature.model.Temperature;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<DayReport> dayReports;

    protected City() { }

    public City(String name) {
        this.name = name;
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<DayReport> getDayReports() {
        return dayReports;
    }

    public Temperature getAverage() {
        double average = this.dayReports.stream().mapToDouble(report -> report.getAverage().getValue()).average().orElse(0);
        return new Temperature(average);
    }

}
