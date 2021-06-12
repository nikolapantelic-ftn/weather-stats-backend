package com.github.nikolapantelicftn.weatherstatsbackend.city.repository;

import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where lower(c.name)=lower(:cityName)")
    Optional<City> findByCityName(@Param("cityName") String cityName);

}
