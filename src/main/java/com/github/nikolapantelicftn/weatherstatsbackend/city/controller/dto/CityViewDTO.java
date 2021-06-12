package com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto;

public class CityViewDTO {

    private Long id;
    private String name;

    public CityViewDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

}
