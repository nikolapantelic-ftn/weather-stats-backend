package com.github.nikolapantelicftn.weatherstatsbackend.temperature.service;

import com.github.nikolapantelicftn.weatherstatsbackend.temperature.model.DayReport;
import com.github.nikolapantelicftn.weatherstatsbackend.temperature.repository.DayReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReportService {

    private final DayReportRepository repository;

    public ReportService(DayReportRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<DayReport> get() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<DayReport> get(List<LocalDate> dates) {
        Objects.requireNonNull(dates);
        return repository.findByDateIn(dates);
    }

    @Transactional(readOnly = true)
    public List<DayReport> get(LocalDate from, LocalDate to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        List<LocalDate> dates = getDatesFromInterval(from, to);
        return get(dates);
    }

    private List<LocalDate> getDatesFromInterval(LocalDate from, LocalDate to) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = LocalDate.from(from);
        while (!current.isAfter(to)) {
            dates.add(LocalDate.from(current));
            current = current.plus(Period.ofDays(1));
        }
        return dates;
    }

    @Transactional
    public DayReport create(DayReport newReport) {
        Objects.requireNonNull(newReport);
        if (newReport.getId() != null && repository.existsById(newReport.getId())) {
            // TODO: Add custom exceptions
            throw new RuntimeException("Day report exists");
        }
        return repository.save(newReport);
    }

}
