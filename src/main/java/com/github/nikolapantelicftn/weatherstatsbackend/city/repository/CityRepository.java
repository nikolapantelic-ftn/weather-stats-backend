package com.github.nikolapantelicftn.weatherstatsbackend.city.repository;

import com.github.nikolapantelicftn.weatherstatsbackend.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where lower(c.name)=lower(:cityName)")
    Optional<City> findByCityName(@Param("cityName") String cityName);

    @Query("select c from City c left join c.dayReports dr left join dr.hourReports hr" +
            " group by c order by avg(hr.temperature.value) desc")
    List<City> findAllSorted();

}
