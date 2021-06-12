package com.github.nikolapantelicftn.weatherstatsbackend.http.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.nikolapantelicftn.weatherstatsbackend.http.client.WeatherResponse;
import com.github.nikolapantelicftn.weatherstatsbackend.temperature.model.DayReport;
import com.github.nikolapantelicftn.weatherstatsbackend.temperature.model.HourReport;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeatherResponseDeserializer extends JsonDeserializer<WeatherResponse> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public WeatherResponse deserialize(JsonParser jsonParser,
                                 DeserializationContext deserializationContext) throws IOException {

        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        int count = rootNode.get("cnt").asInt();
        ArrayNode jsonHourReportsArray = (ArrayNode) rootNode.get("list");

        List<JsonNode> jsonHourReports = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            jsonHourReports.add(jsonHourReportsArray.get(i));
        }

        List<HourReport> hourReports = getHourReportsFromJsonList(jsonHourReports);
        List<DayReport> dayReports = groupHourReports(hourReports);
        return new WeatherResponse(dayReports);
    }

    private List<HourReport> getHourReportsFromJsonList(List<JsonNode> jsonHourReports) {
        return jsonHourReports.stream().map(json -> {
            Double temperature = json.get("main").get("temp").asDouble();
            LocalDateTime dateTime = LocalDateTime.parse(json.get("dt_txt").asText(), FORMATTER);
            return new HourReport(dateTime, temperature);
        }).collect(Collectors.toList());
    }

    private List<DayReport> groupHourReports(List<HourReport> hourReports) {
        Map<LocalDate, List<HourReport>> groupedHourReports =
                hourReports.stream().collect(Collectors.groupingBy(hourReport -> hourReport.getTime().toLocalDate()));

        List<DayReport> dayReports = new ArrayList<>();
        groupedHourReports.keySet().forEach(key -> {
            dayReports.add(new DayReport(key, groupedHourReports.get(key)));
        });

        return dayReports;
    }

}
