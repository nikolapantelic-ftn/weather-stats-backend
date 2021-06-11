package com.github.nikolapantelicftn.weatherstatsbackend.city.repository;

import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

}
