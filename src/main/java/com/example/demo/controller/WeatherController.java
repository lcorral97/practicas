package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Weather;
import com.example.demo.service.WeatherService;

import io.swagger.annotations.ApiOperation;

@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	@ApiOperation(value="Saca el temporal de un API externo, lo mete en la BD")
	@GetMapping("/weather")
	public ResponseEntity<Weather> nuevoWeather(@RequestParam(defaultValue="Bilbao") String q) throws CustomException{
		Weather weather = weatherService.crearWeatherDeUnJSON(q);
		if (weather == null) {
			return new ResponseEntity<>(weather, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(weather);
		}
	}

	@ApiOperation(value="Exporta un excel con el código en Base64")
	@GetMapping("/exportExcel")
	public ResponseEntity<?> exportarExcel() throws CustomException{
		weatherService.crearNuevoExcel();
		String ficheroDecodificado = weatherService.ficheroBase64();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		headers.add("Content-Disposition", "attachment; filename=fichero.xls");
		ResponseEntity<String> re = new ResponseEntity<>(ficheroDecodificado, headers, HttpStatus.CREATED);
		return re;
	}
}
