package com.github.nikolapantelicftn.weatherstatsbackend.weather.service;

import com.github.nikolapantelicftn.weatherstatsbackend.exceptions.EntityExistsException;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.DayReport;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.model.HourReport;
import com.github.nikolapantelicftn.weatherstatsbackend.weather.repository.DayReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private DayReportRepository repository;

    @InjectMocks
    private ReportService service;

    private static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    void getAll_Success() {
        List<HourReport> hourReports = List.of(new HourReport(NOW, 0d));
        List<DayReport> reports = List.of(new DayReport(NOW.toLocalDate(), hourReports));
        when(repository.findAll()).thenReturn(reports);

        List<DayReport> result = service.get();

        assertTrue(hourReports.containsAll(result) && result.containsAll(hourReports));
    }

    @Test
    void create_Success() {
        List<HourReport> hourReports = List.of(new HourReport(NOW, 0d));
        DayReport dayReport = new DayReport(NOW.toLocalDate(), hourReports);
        when(repository.save(dayReport)).thenReturn(dayReport);

        DayReport result = service.create(dayReport);

        assertEquals(dayReport, result);
    }

    @Test
    void create_DayReportExists() {
        Long id = 1L;
        List<HourReport> hourReports = List.of(new HourReport(NOW, 0d));
        DayReport dayReport = new DayReport(id, NOW.toLocalDate(), hourReports);
        when(repository.existsById(id)).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> service.create(dayReport));
    }

    @Test
    void create_NullArgument() {
        assertThrows(NullPointerException.class, () -> service.create(null));
    }

    @Test
    void getByDates_Success() {
        LocalDateTime tomorrow = NOW.plus(Duration.ofDays(1));
        List<LocalDate> dates = List.of(NOW.toLocalDate(), tomorrow.toLocalDate());
        List<HourReport> todaysHourReports = List.of(new HourReport(NOW, 0d));
        List<HourReport> tomorrowsHourReports = List.of(new HourReport(tomorrow, 0d));
        DayReport todaysDayReport = new DayReport(NOW.toLocalDate(), todaysHourReports);
        DayReport tomorrowsDayReport = new DayReport(tomorrow.toLocalDate(), tomorrowsHourReports);
        List<DayReport> reports = List.of(todaysDayReport, tomorrowsDayReport);
        when(repository.findByDateIn(dates)).thenReturn(reports);

        List<DayReport> result = service.get(dates);

        assertTrue(reports.containsAll(result) && result.containsAll(reports));
    }

    @Test
    void getByDateInterval_Success() {
        LocalDateTime tomorrow = NOW.plus(Duration.ofDays(1));
        List<LocalDate> dates = List.of(NOW.toLocalDate(), tomorrow.toLocalDate());
        List<HourReport> todaysHourReports = List.of(new HourReport(NOW, 0d));
        List<HourReport> tomorrowsHourReports = List.of(new HourReport(tomorrow, 0d));
        DayReport todaysDayReport = new DayReport(NOW.toLocalDate(), todaysHourReports);
        DayReport tomorrowsDayReport = new DayReport(tomorrow.toLocalDate(), tomorrowsHourReports);
        List<DayReport> reports = List.of(todaysDayReport, tomorrowsDayReport);

        when(repository.findByDateIn(dates)).thenReturn(reports);

        List<DayReport> result = service.get(NOW.toLocalDate(), tomorrow.toLocalDate());

        assertTrue(reports.containsAll(result) && result.containsAll(reports));
    }

    @Test
    void getByDateInterval_NullArguments() {
        assertThrows(NullPointerException.class, () -> service.get(null, null));
    }

    @Test
    void getByDates_NullArgument() {
        assertThrows(NullPointerException.class, () -> service.get(null));
    }

}
