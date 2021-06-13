package com.github.nikolapantelicftn.weatherstatsbackend.weather.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUpdate {

    private final ReportService service;


    public ScheduledUpdate(ReportService service) {
        this.service = service;
    }

    /**
     * Updates weather reports every hour.
     */
    @Scheduled(cron = "0 * * * * *")
    public void updateWeatherReports() {
        service.initReports();
    }

}
