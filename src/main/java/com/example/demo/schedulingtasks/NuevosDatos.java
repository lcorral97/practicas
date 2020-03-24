package com.example.demo.schedulingtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.controller.Controller;
import com.example.demo.exception.CustomException;

@Component
public class NuevosDatos {

	@Autowired
	private Controller controller;
	
	@Scheduled(fixedRate = 3600000)
	private void guardarDatos() throws CustomException {
		controller.nuevoWeather();
		controller.crearNuevoExcel();
	}
}
