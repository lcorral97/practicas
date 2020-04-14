package com.example.demo.schedulingtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Empleado;
import com.example.demo.service.EmpDeptoService;
import com.example.demo.service.WeatherService;


@SuppressWarnings("unused")
@Component
public class NuevosDatos {

	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private EmpDeptoService empDeptoService;
	
	//@Scheduled(fixedRate = 86400000)
	private void guardarDatos() throws CustomException {
		for(Empleado e : empDeptoService.getEmpleados()) {
			weatherService.crearWeatherDeUnJSON(e.getCiudad());
		}
	}
}
