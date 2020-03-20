package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Weather;

public interface WeatherService {

	Weather nuevoWeather(Weather w) throws CustomException;
	List<Weather> getWeathers() throws CustomException;
}
