package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.SpringbootApplication;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Weather;
import com.example.demo.service.WeatherService;
import com.example.demo.util.ApiPropertyUtil;
import com.google.gson.Gson;

import io.swagger.annotations.ApiOperation;

@RestController
public class WeatherController {

	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private WeatherService weatherService;
	
	private final String RUTA = "api.properties";
	
	@Value("classpath:" + RUTA)
	private Resource r;
	
	@ApiOperation(value="Saca el temporal de un API externo, lo mete en la BD")
	@GetMapping("/weather")
	public ResponseEntity<Weather> nuevoWeather(@RequestParam String q) throws CustomException{
		ApiPropertyUtil apu = new ApiPropertyUtil(r);
		String jsonString = rest.getForObject(q!=null?apu.getPropiedad("Url") + q:apu.getPropiedad("Url")+"Bilbao", String.class);
		Weather w = null;
		Gson gson = new Gson();
		try {
			JSONObject json = new JSONObject(jsonString).getJSONObject("current");
			w = gson.fromJson(json.toString(), Weather.class);
			w.setCity(new JSONObject(jsonString).getJSONObject("location").getString("name"));
			w = weatherService.nuevoWeather(w);
		} catch (JSONException e) {
			CustomException ce = new CustomException("Error en el JSON: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
		if (w == null) {
			return new ResponseEntity<>(w, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(w);
		}
	}

	@ApiOperation(value="Exporta un excel con el código en Base64")
	@GetMapping("/exportExcel")
	public ResponseEntity<?> exportarExcel() throws CustomException{
		try {
			weatherService.crearNuevoExcel();
			File fichero = new File("fichero.xls");
			FileInputStream in = new FileInputStream(fichero);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			headers.add("Content-Disposition", "attachment; filename=fichero.xls");
			byte[] bytes = new byte[(int)fichero.length()];
			in.read(bytes);
			String ficheroDecodificado = Base64.getEncoder().encodeToString(bytes);
			ResponseEntity<String> re = new ResponseEntity<>(ficheroDecodificado, headers, HttpStatus.CREATED);
			in.close();
			return re;
		} catch(Exception e) {
			CustomException ce = new CustomException("Error al exportar el xls: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
	}
}
