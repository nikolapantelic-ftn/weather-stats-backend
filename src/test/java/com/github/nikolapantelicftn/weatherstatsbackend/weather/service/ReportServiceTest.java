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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        List<DayReport> expected = List.of(new DayReport(NOW.toLocalDate(), hourReports));
        when(repository.findAll()).thenReturn(expected);

        List<DayReport> result = service.get();

        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    void create_Success() {
        List<HourReport> hourReports = List.of(new HourReport(NOW, 0d));
        DayReport expected = new DayReport(NOW.toLocalDate(), hourReports);
        when(repository.save(expected)).thenReturn(expected);

        DayReport result = service.create(expected);

        assertEquals(expected, result);
    }

    @Test
    void create_DayReportExists() {
        final Long id = 1L;
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
        LocalDateTime tomorrow = NOW.plusDays(1);
        List<LocalDate> dates = List.of(NOW.toLocalDate(), tomorrow.toLocalDate());
        List<HourReport> todaysHourReports = List.of(new HourReport(NOW, 0d));
        List<HourReport> tomorrowsHourReports = List.of(new HourReport(tomorrow, 0d));
        DayReport todaysDayReport = new DayReport(NOW.toLocalDate(), todaysHourReports);
        DayReport tomorrowsDayReport = new DayReport(tomorrow.toLocalDate(), tomorrowsHourReports);
        List<DayReport> expected = List.of(todaysDayReport, tomorrowsDayReport);
        when(repository.findByDateIn(dates)).thenReturn(expected);

        List<DayReport> result = service.get(dates);

        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    void getByDateInterval_Success() {
        LocalDateTime tomorrow = NOW.plusDays(1);
        List<LocalDate> dates = List.of(NOW.toLocalDate(), tomorrow.toLocalDate());
        List<HourReport> todaysHourReports = List.of(new HourReport(NOW, 0d));
        List<HourReport> tomorrowsHourReports = List.of(new HourReport(tomorrow, 0d));
        DayReport todaysDayReport = new DayReport(NOW.toLocalDate(), todaysHourReports);
        DayReport tomorrowsDayReport = new DayReport(tomorrow.toLocalDate(), tomorrowsHourReports);
        List<DayReport> expected = List.of(todaysDayReport, tomorrowsDayReport);

        when(repository.findByDateIn(dates)).thenReturn(expected);

        List<DayReport> result = service.get(NOW.toLocalDate(), tomorrow.toLocalDate());

        assertThat(result).hasSameElementsAs(expected);
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
