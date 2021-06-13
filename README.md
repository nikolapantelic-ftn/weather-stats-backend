# weather-stats-backend
Backend application for a weather app showing statistics for given cities.

- Spring Boot v2.5.1

## Running application and tests
Using maven:

`mvn spring-boot:run`

`mvn test`

## Description
Application uses a weather API from https://openweathermap.org/. API key is included inside configuration file for easier startup (set API_KEY environment variable to provide another API key).

Weather data is loaded for 3 cities (**London**, **Utrecht** and **Belgrade**) on appplication startup, and is updated every hour after initial application startup using cron expression.

Cities and country codes are populated from property configuration.

Enpoints are documented using open-api, available at `/swagger-ui.html` endpoint.

CORS requests are open only to the frontend application (http://localhost:4200 origin) and only GET and OPTIONS methods are available.

Unit and integration tests are created for all services (including custom json deserializer for openweathermap API) and API enpoints.

Integration tests use data loaded from the openweathermap API.

### Logging
Using Logback over Slf4j for logging. Logger uses console and rolling file appenders.
Logging service operations, exception ocurrences and implemented a request logging filter.

## TODO:
- Separate jackson object mapper is created because custom deserializer breaks LocalDate deserialization. Fix the issue and use single object mapper bean.
- Cities are sorted by average temperature in descending order. Add the option to choose between asc and desc.
- Write logs that are more suitable for log parsing utilities.
