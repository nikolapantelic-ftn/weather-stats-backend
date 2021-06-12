package com.github.nikolapantelicftn.weatherstatsbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ConfigProperties {

    private List<String> cities;
    private String weatherApi;
    private String apiKey;

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cityNames) {
        this.cities = cityNames;
    }

    public String getWeatherApi() {
        return weatherApi;
    }

    public void setWeatherApi(String weatherApi) {
        this.weatherApi = weatherApi;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
