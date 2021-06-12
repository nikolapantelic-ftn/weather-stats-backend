package com.github.nikolapantelicftn.weatherstatsbackend.temperature.repository;

import com.github.nikolapantelicftn.weatherstatsbackend.temperature.model.DayReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DayReportRepository extends JpaRepository<DayReport, Long> {

    List<DayReport> findByDateIn(List<LocalDate> dates);

}
