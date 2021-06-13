package com.github.nikolapantelicftn.weatherstatsbackend.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikolapantelicftn.weatherstatsbackend.city.controller.dto.CityViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.controller.dto.DayReportViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.DayReport;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.repository.DayReportRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DayReportRepository repository;

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);

    @Test
    void getByDateInterval_Success() throws Exception {
        final String queryParams = "?from=" + TODAY + "&to=" + TOMORROW;
        List<DayReport> reports = repository.findByDateIn(List.of(TODAY, TOMORROW));
        List<DayReportViewDTO> expected = reports.stream()
                .map(report -> modelMapper.map(report, DayReportViewDTO.class))
                .collect(Collectors.toList());

        MvcResult result = mvc.perform(get("/api/reports" + queryParams)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultJson = result.getResponse().getContentAsString();
        List<DayReportViewDTO> resultList = List.of(objectMapper.readValue(resultJson, DayReportViewDTO[].class));

        assertThat(resultList).hasSameElementsAs(expected);
    }

    @Test
    void getByDateInterval_Empty() throws Exception {
        final String queryParams = "?from=" + TODAY.minusDays(3) + "&to=" + TODAY.minusDays(2);

        MvcResult result = mvc.perform(get("/api/reports" + queryParams)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultJson = result.getResponse().getContentAsString();
        List<DayReportViewDTO> resultList = List.of(objectMapper.readValue(resultJson, DayReportViewDTO[].class));

        assertThat(resultList).isEmpty();
    }

}
