package com.github.nikolapantelicftn.weatherstatsbackend.city.service;

import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import com.github.nikolapantelicftn.weatherstatsbackend.city.repository.CityRepository;
import com.github.nikolapantelicftn.weatherstatsbackend.config.ConfigProperties;
import com.github.nikolapantelicftn.weatherstatsbackend.exceptions.EntityExistsException;
import com.github.nikolapantelicftn.weatherstatsbackend.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class CityService {

    private static final Logger log = LoggerFactory.getLogger(CityService.class);

    private final ConfigProperties properties;
    private final CityRepository repository;


    public CityService(ConfigProperties properties, CityRepository repository) {
        this.properties = properties;
        this.repository = repository;
    }

    @PostConstruct
    @Transactional
    public void initCities() {
        log.info("Initializing cities.");

        List<String> cityNames = properties.getCities();
        cityNames.forEach(cityName -> create(new City(cityName)));

        log.info("Finished initializing cities");
    }

    @Transactional
    public City create(City newCity) {
        Objects.requireNonNull(newCity);
        if (newCity.getId() != null && repository.existsById(newCity.getId())) {
            throw new EntityExistsException("City already exists.");
        }
        return repository.save(newCity);
    }

    @Transactional(readOnly = true)
    public City get(String cityName) {
        Objects.requireNonNull(cityName);
        log.info("Fetching city with name '{}'", cityName);

        return repository.findByCityName(cityName)
                .orElseThrow(() -> new EntityNotFoundException("City not found."));
    }

    @Transactional(readOnly = true)
    public List<City> get() {
        log.info("Fetching all cities.");
        return repository.findAll();
    }

}
