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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityService service;

    private static final String CITY_NAME = "City Name";
    private static final String COUNTRY_CODE = "CC";

    @Test
    void create_Success() {
        City expected = new City(CITY_NAME, COUNTRY_CODE);

        Mockito.when(repository.save(expected)).thenReturn(expected);

        City result = service.create(expected);

        Mockito.verify(repository, Mockito.times(1)).save(expected);

        assertEquals(expected, result);
    }

    @Test
    void create_CityExists() {
        final Long id = 1L;
        City newCity = new City(id, CITY_NAME, COUNTRY_CODE);

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
        City expected = new City(CITY_NAME, COUNTRY_CODE);

        Mockito.when(repository.findByCountryCodeAndName(COUNTRY_CODE, CITY_NAME)).thenReturn(Optional.of(expected));

        City result = service.get(COUNTRY_CODE, CITY_NAME);

        assertEquals(expected, result);
    }

    @Test
    void getByCityName_NotExists() {
        Mockito.when(repository.findByCountryCodeAndName(COUNTRY_CODE, CITY_NAME)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> service.get(COUNTRY_CODE, CITY_NAME)
        );
    }

    @Test
    void getAll_Success() {
        List<City> expected = List.of(new City(CITY_NAME, COUNTRY_CODE));
        Mockito.when(repository.findAllSorted()).thenReturn(expected);

        List<City> result = service.get();

        assertThat(result).hasSameElementsAs(expected);
    }

}
