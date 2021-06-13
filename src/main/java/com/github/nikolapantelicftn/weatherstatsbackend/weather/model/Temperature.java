package com.github.nikolapantelicftn.weatherstatsbackend.weather.model;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
