package com.github.nikolapantelicftn.weatherstatsbackend.weather.model;

import javax.persistence.Embeddable;

@Embeddable
public class Temperature {

    private Double value;

    public Temperature(Double value) {
        this.value = value;
    }

    protected Temperature() {}

    public Double getValue() {
        return value;
    }

    public Double getCelsius() {
        return value - 273.5f;
    }

    public Double getFahrenheit() {
        return (value - 273.15f) * 9 / 5 + 32;
    }

}
