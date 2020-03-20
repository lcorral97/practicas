package com.example.demo.dao;

import java.util.List;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Weather;

public interface WeatherDAO {

	Weather nuevoWeather(Weather w) throws CustomException;
	List<Weather> getWeathers() throws CustomException;
	
}
