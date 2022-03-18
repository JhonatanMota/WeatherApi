package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherApiRestController {

  @Autowired
  private WeatherService weatherService;

  @PostMapping
  public ResponseEntity<Weather> addWeather(@Valid @RequestBody Weather weather) {
    return weatherService.addWeather(weather);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Weather> getWeather(@PathVariable(name = "id") Integer id) {
    Weather weather = weatherService.getWeather(id);

    return new ResponseEntity<>(weather, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Weather>> getWeatherFilter(
      @RequestParam(name = "date", required = false) String date,
      @RequestParam(name = "city", required = false) List<String> cities,
      @RequestParam(name = "sort", required = false) String sortMethod) throws ParseException {

      List<Weather> weatherList = weatherService.getWeatherByFilter(date,cities,sortMethod);
      return new ResponseEntity<>(weatherList, HttpStatus.OK);
  }

}
