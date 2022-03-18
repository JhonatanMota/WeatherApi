package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface WeatherService {

  ResponseEntity<Weather> addWeather(Weather weather);

  Weather getWeather(Integer id);

  List<Weather> getWeatherByFilter(String date, List<String> cities, String sortMethod)
      throws ParseException;

}
