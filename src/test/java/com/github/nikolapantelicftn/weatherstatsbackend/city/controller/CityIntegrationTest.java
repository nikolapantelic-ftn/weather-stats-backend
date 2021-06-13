package com.github.nikolapantelicftn.weatherstatsbackend.city.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto.CityViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import com.github.nikolapantelicftn.weatherstatsbackend.city.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class CityIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CityRepository repository;

    @Test
    void getAll_Success() throws Exception {
        List<City> cities = repository.findAllSorted();
        List<CityViewDTO> expected = cities.stream()
                .map(city -> modelMapper.map(city, CityViewDTO.class)).collect(Collectors.toList());

        MvcResult result = mvc.perform(get("/api/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultJson = result.getResponse().getContentAsString();
        List<CityViewDTO> resultList = List.of(objectMapper.readValue(resultJson, CityViewDTO[].class));

        assertThat(expected).hasSameElementsAs(resultList);
    }

    @Test
    void getByCityName_Success() throws Exception {
        final String cityName = "London";
        final String countryCode = "UK";
        City london = repository.findByCountryCodeAndName(countryCode, cityName).orElseThrow();
        CityViewDTO expected = modelMapper.map(london, CityViewDTO.class);

        MvcResult result = mvc.perform(get("/api/cities/" + countryCode + "/" + cityName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultJson = result.getResponse().getContentAsString();
        CityViewDTO resultCity = objectMapper.readValue(resultJson, CityViewDTO.class);

        assertEquals(expected, resultCity);
    }

    @Test
    void getByCityName_NotFound() throws Exception {
        final String cityName = "Non existent city";
        final String countryCode = "UK";
        mvc.perform(get("/api/cities/" + countryCode + "/" + cityName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
