package com.github.nikolapantelicftn.weatherstatsbackend.city.controller;

import com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto.CityViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import com.github.nikolapantelicftn.weatherstatsbackend.city.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/cities")
public class CityController {

    private final CityService service;
    private final ModelMapper mapper;

    public CityController(CityService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<CityViewDTO> getAll() {
        List<City> allCities = service.get();
        return allCities.stream()
                .map(city -> mapper.map(city, CityViewDTO.class))
                .collect(Collectors.toList());
    }

}
