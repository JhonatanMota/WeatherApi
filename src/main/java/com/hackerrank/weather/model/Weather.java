package com.hackerrank.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Weather implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Date date;
  private Float lat;
  private Float lon;
  private String city;
  private String state;

  @ElementCollection
  private List<Double> temperatures;

  public Weather(Date date, Float lat, Float lon, String city, String state,
      List<Double> temperatures) {
    this.date = date;
    this.lat = lat;
    this.lon = lon;
    this.city = city;
    this.state = state;
    this.temperatures = temperatures;
  }

  public Weather() {
  }

}
