package com.github.nikolapantelicftn.weatherstatsbackend.city.controller;

import com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto.CityViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import com.github.nikolapantelicftn.weatherstatsbackend.city.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
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

    @Operation(summary = "Gets all cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Array of cities", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                            schema = @Schema(implementation = CityViewDTO.class)
                    ))
            })
    })

    @GetMapping()
    public List<CityViewDTO> getAll() {
        List<City> allCities = service.get();
        return allCities.stream()
                .map(city -> mapper.map(city, CityViewDTO.class))
                .collect(Collectors.toList());
    }

}
