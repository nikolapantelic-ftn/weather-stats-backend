package com.github.nikolapantelicftn.weatherstatsbackend.temperature.controller;

import com.github.nikolapantelicftn.weatherstatsbackend.temperature.controller.dto.DayReportViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.temperature.model.DayReport;
import com.github.nikolapantelicftn.weatherstatsbackend.temperature.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/reports")
public class ReportController {

    private final ReportService service;
    private final ModelMapper mapper;

    public ReportController(ReportService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DayReportViewDTO> get(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        List<DayReport> found = service.get(from, to);
        return found.stream()
                .map(report -> mapper.map(report, DayReportViewDTO.class))
                .collect(Collectors.toList());
    }

}
