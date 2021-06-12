package com.github.nikolapantelicftn.weatherstatsbackend.city.service;

import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import com.github.nikolapantelicftn.weatherstatsbackend.city.repository.CityRepository;
import com.github.nikolapantelicftn.weatherstatsbackend.exceptions.EntityExistsException;
import com.github.nikolapantelicftn.weatherstatsbackend.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityService service;

    private static final String CITY_NAME = "City Name";

    @Test
    void create_Success() {
        City newCity = new City(CITY_NAME);

        Mockito.when(repository.save(newCity)).thenReturn(newCity);

        City result = service.create(newCity);

        Mockito.verify(repository, Mockito.times(1)).save(newCity);

        assertEquals(newCity, result);
    }

    @Test
    void create_CityExists() {
        Long id = 1L;
        City newCity = new City(id, CITY_NAME);

        Mockito.when(repository.existsById(id)).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> {
            service.create(newCity);
        });
    }

    @Test
    void create_NullArgument() {
        assertThrows(NullPointerException.class,
                () -> service.create(null)
        );
    }

    @Test
    void getByCityName_Success() {
        City newCity = new City(CITY_NAME);

        Mockito.when(repository.findByCityName(CITY_NAME)).thenReturn(Optional.of(newCity));

        City result = service.get(CITY_NAME);

        assertEquals(newCity, result);
    }

    @Test
    void getByCityName_NotExists() {
        Mockito.when(repository.findByCityName(CITY_NAME)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> service.get(CITY_NAME)
        );
    }

    @Test
    void getAll_Success() {
        List<City> cities = List.of(new City(CITY_NAME));
        Mockito.when(repository.findAll()).thenReturn(cities);

        List<City> result = service.get();

        assertTrue(cities.containsAll(result) && result.containsAll(cities));
    }

}
