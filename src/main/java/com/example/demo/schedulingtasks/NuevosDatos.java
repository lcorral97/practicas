package com.example.demo.schedulingtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.controller.WeatherController;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Empleado;
import com.example.demo.service.EmpDeptoService;

@Component
public class NuevosDatos {

	@Autowired
	private WeatherController weatherController;
	
	@Autowired
	private EmpDeptoService empDeptoService;
	
	@Scheduled(fixedRate = 3600000)
	private void guardarDatos() throws CustomException {
		for(Empleado e : empDeptoService.getEmpleados()) {
			weatherController.nuevoWeather(e.getCiudad());
		}
	}
}
