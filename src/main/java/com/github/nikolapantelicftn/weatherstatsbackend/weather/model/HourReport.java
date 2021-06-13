package com.github.nikolapantelicftn.weatherstatsbackend.weather.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class HourReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    @Embedded
    private Temperature temperature;

    public HourReport(LocalDateTime time, Temperature temperature) {
        this.time = time;
        this.temperature = temperature;
    }

    public HourReport(LocalDateTime time, Double temperature) {
        this.time = time;
        this.temperature = new Temperature(temperature);
    }

    protected HourReport() {}

    public Long getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Temperature getTemperature() {
        return temperature;
    }

}
