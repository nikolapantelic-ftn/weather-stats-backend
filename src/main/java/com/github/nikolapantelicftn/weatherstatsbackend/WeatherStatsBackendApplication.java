package com.github.nikolapantelicftn.weatherstatsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WeatherStatsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherStatsBackendApplication.class, args);
	}

}
