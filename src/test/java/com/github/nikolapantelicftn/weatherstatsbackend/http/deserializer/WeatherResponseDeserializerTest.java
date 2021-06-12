package com.github.nikolapantelicftn.weatherstatsbackend.http.deserializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nikolapantelicftn.weatherstatsbackend.http.client.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class WeatherResponseDeserializerTest {

    private final WeatherResponseDeserializer deserializer = new WeatherResponseDeserializer();

    @Test
    public void deserialize_Success() throws IOException {
        String json = "{\"cod\":\"200\",\"message\":0,\"cnt\":1,\"list\":[{\"dt\":1623520800," +
                "\"main\":{\"temp\":297.26,\"feels_like\":296.88,\"temp_min\":295.15,\"temp_max\"" +
                ":297.26,\"pressure\":1025,\"sea_level\":1025,\"grnd_level\":1022,\"humidity\":44," +
                "\"temp_kf\":2.11},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":" +
                "\"few clouds\",\"icon\":\"02d\"}],\"clouds\":{\"all\":19},\"wind\":{\"speed\":3.65," +
                "\"deg\":348,\"gust\":3.32},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"}," +
                "\"dt_txt\":\"2021-06-12 18:00:00\"}],\"city\":{\"id\":2643743,\"name\":\"London\"," +
                "\"coord\":{\"lat\":51.5085,\"lon\":-0.1257},\"country\":\"GB\",\"population\":1000000," +
                "\"timezone\":3600,\"sunrise\":1623469398,\"sunset\":1623529057}}";
        JsonFactory factory = new JsonFactory();
        factory.setCodec(new ObjectMapper());
        JsonParser parser = factory.createParser(json);

        WeatherResponse deserialized = deserializer.deserialize(parser, null);
        assertNotNull(deserialized);
        assertNotNull(deserialized.getWeatherReport());
        assertEquals(1, deserialized.getWeatherReport().size());
    }

}
