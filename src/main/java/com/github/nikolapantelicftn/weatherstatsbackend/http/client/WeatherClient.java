package com.github.nikolapantelicftn.weatherstatsbackend.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.nikolapantelicftn.weatherstatsbackend.config.ConfigProperties;
import com.github.nikolapantelicftn.weatherstatsbackend.exceptions.ApiException;
import com.github.nikolapantelicftn.weatherstatsbackend.http.deserializer.WeatherResponseDeserializer;
import com.github.nikolapantelicftn.weatherstatsbackend.temperature.model.DayReport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class WeatherClient {

    private WebClient client;
    private ObjectMapper mapper;
    private final String apiKey;
    private static final String URI_PREFIX = "/data/2.5/forecast?";

    public WeatherClient(ConfigProperties properties) {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(WeatherResponse.class, new WeatherResponseDeserializer());
        mapper.registerModule(module);
        this.apiKey = properties.getApiKey();
        this.client = WebClient.builder()
                .baseUrl(properties.getWeatherApi())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

    }

    public List<DayReport> getForCity(String name) {
        String response = this.client.get()
                .uri(URI_PREFIX + getQueryParams(name, apiKey))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class)
                .block();

        return getDayReportsFromResponse(response);
    }

    private List<DayReport> getDayReportsFromResponse(String response) {
        try {
            WeatherResponse weather = mapper.readValue(response, WeatherResponse.class);
            return weather.getWeatherReport();
        } catch (JsonProcessingException e) {
            throw new ApiException("Error parsing API response!");
        }
    }

    private String getQueryParams(String city, String apiKey) {
        return String.format("q=%s&appid=%s", city, apiKey);
    }

}
