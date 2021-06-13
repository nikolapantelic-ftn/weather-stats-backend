package com.github.nikolapantelicftn.weatherstatsbackend.weather.controller;

import com.github.nikolapantelicftn.weatherstatsbackend.weather.controller.dto.DayReportViewDTO;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.DayReport;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.service.ReportService;
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

    @Operation(summary = "Gets daily reports within a time interval specified by query parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Array of daily reports", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                            schema = @Schema(implementation = DayReportViewDTO.class)
                    ))
            })
    })
    @GetMapping
    public List<DayReportViewDTO> get(@RequestParam String from, @RequestParam String to) {
        List<DayReport> found = service.get(LocalDate.parse(from), LocalDate.parse(to));
        return found.stream()
                .map(report -> mapper.map(report, DayReportViewDTO.class))
                .collect(Collectors.toList());
    }

}
