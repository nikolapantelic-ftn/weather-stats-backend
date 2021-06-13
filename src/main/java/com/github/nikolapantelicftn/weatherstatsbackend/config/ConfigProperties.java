package com.github.nikolapantelicftn.weatherstatsbackend.config;

import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ConfigProperties {

    private List<City> cities;
    private String weatherApi;
    private String apiKey;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
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
