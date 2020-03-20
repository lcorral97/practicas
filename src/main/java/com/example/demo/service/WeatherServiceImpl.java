package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.WeatherDAO;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherDAO dao;
	
	@Override
	public Weather nuevoWeather(Weather w) throws CustomException {
		return dao.nuevoWeather(w);
	}

	@Override
	public List<Weather> getWeathers() throws CustomException {
		return dao.getWeathers();
	}

}
