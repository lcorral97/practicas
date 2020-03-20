package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.SpringbootApplication;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Weather;
import com.example.demo.service.WeatherService;

@RestController
public class WeatherController {

	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private WeatherService service;
	
	private String url = "http://api.weatherstack.com/current?access_key=194a049cedbc41295ac29d7559f9d0c1&query=Bilbao";
	
	@GetMapping("/weather")
	public ResponseEntity<Weather> nuevoWeather() throws CustomException{
		String jsonString = rest.getForObject(url, String.class);
		Weather w = null;
		try {
			JSONObject json = new JSONObject(jsonString);
			w = new Weather();
			w.setObservation_time(json.getJSONObject("current").getString("observation_time"));
			w.setTemperature(json.getJSONObject("current").getInt("temperature"));
			w.setWeather_descriptions(json.getJSONObject("current").getJSONArray("weather_descriptions").getString(0));
			w.setWind_speed(json.getJSONObject("current").getInt("wind_speed"));
			w.setWind_degree(json.getJSONObject("current").getInt("wind_degree"));
			w.setWind_direction(json.getJSONObject("current").getString("wind_dir"));
			w.setPressure(json.getJSONObject("current").getInt("pressure"));
			w.setPrecip(json.getJSONObject("current").getDouble("precip"));
			w.setHumidity(json.getJSONObject("current").getInt("humidity"));
			w = service.nuevoWeather(w);
		} catch (JSONException e) {
			CustomException ce = new CustomException("Error en el JSON: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
		return ResponseEntity.ok(w);
	}
	
	@GetMapping("/datosExcel")
	public ResponseEntity<String> crearNuevoExcel() throws CustomException{
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet hoja = workbook.createSheet();
			int numFila = 0;
			List<Weather> ws = service.getWeathers();
			for (Weather w : ws) {
				numFila = nuevaFila(hoja, numFila, w);
			}
			File fichero = new File("fichero.xls");
			FileOutputStream ficheroVuelta = new FileOutputStream(fichero);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(ficheroVuelta);
			ficheroVuelta.close();
			LoggerFactory.getLogger(SpringbootApplication.class)
				.info("Fichero xls creado");
		} catch(Exception e) {
			CustomException ce = new CustomException("Error al crear el xls: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
		return new ResponseEntity<>("Fichero xls creado", HttpStatus.CREATED);
	}


	@GetMapping("/exportExcel")
	public ResponseEntity<?> exportarExcel() throws CustomException{
		try {
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
	
	private int nuevaFila(HSSFSheet hoja, int numFila, Weather w) {
		HSSFRow fila = hoja.createRow(numFila++);
		int numCelda = 0;
		HSSFCell celdaID = fila.createCell(numCelda++);
		celdaID.setCellValue(w.getId());
		HSSFCell celdaObservation_Time = fila.createCell(numCelda++);
		celdaObservation_Time.setCellValue(w.getObservation_time());
		HSSFCell celdaTemperature = fila.createCell(numCelda++);
		celdaTemperature.setCellValue(w.getTemperature());
		HSSFCell celdaWeather_Descriptions = fila.createCell(numCelda++);
		celdaWeather_Descriptions.setCellValue(w.getWeather_descriptions());
		HSSFCell celdaWind_Speed = fila.createCell(numCelda++);
		celdaWind_Speed.setCellValue(w.getWind_speed());
		HSSFCell celdaWind_Degree = fila.createCell(numCelda++);
		celdaWind_Degree.setCellValue(w.getWind_degree());
		HSSFCell celdaWind_Direction = fila.createCell(numCelda++);
		celdaWind_Direction.setCellValue(w.getWind_direction());
		HSSFCell celdaPressure = fila.createCell(numCelda++);
		celdaPressure.setCellValue(w.getPressure());
		HSSFCell celdaPrecip = fila.createCell(numCelda++);
		celdaPrecip.setCellValue(w.getPrecip());
		HSSFCell celdaHumidity = fila.createCell(numCelda++);
		celdaHumidity.setCellValue(w.getHumidity());
		return numFila;
	}
	
}
