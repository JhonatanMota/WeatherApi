package com.hackerrank.weather.service.impl;

import com.hackerrank.weather.exception.ResourceNotFoundException;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import com.hackerrank.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

  private static final String ID_STR = "id";
  private static final String WEATHER_STR = "Weather";
  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Autowired
  private WeatherRepository weatherRepository;

  @Override public ResponseEntity<Weather> addWeather(Weather weather) {
    Weather newWeather = weatherRepository.save(weather);
    return new ResponseEntity<>(newWeather, HttpStatus.CREATED);
  }

  @Override public Weather getWeather(Integer id) {
    return weatherRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(WEATHER_STR, ID_STR, id));

  }

  @Override
  public List<Weather> getWeatherByFilter(String date, List<String> cities, String sortMethod)
      throws ParseException {

    Sort sort = Sort.by("id");
    if (sortMethod != null && !sortMethod.isEmpty())
      if ("date".equals(sortMethod))
        sort = Sort.by("date");
      else if ("-date".equals(sortMethod))
        sort = Sort.by("date").descending();

    if (date != null && !date.isEmpty()) {
      return weatherRepository.findByDate(simpleDateFormat.parse(date));

    }

    if (cities != null && !cities.isEmpty())
      return weatherRepository.findAllByCityInIgnoreCase(cities,
          PageRequest.of(0, 10, sort));

    return weatherRepository.findAll(sort);

  }
}
