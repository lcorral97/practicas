package com.example.demo.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.SpringbootApplication;
import com.example.demo.dao.WeatherDAO;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Weather;
import com.example.demo.service.WeatherService;
import com.example.demo.util.PropertyUtil;
import com.google.gson.Gson;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherDAO dao;

	private final String RUTA = "properties/config.properties";
	
	@Value("classpath:" + RUTA)
	private Resource r;

	@Autowired
	private RestTemplate rest;
	@Override
	public Weather nuevoWeather(Weather w) throws CustomException {
		return dao.nuevoWeather(w);
	}

	@Override
	public List<Weather> getWeathers() throws CustomException {
		return dao.getWeathers();
	}

	@Override
	public void crearNuevoExcel() throws CustomException {
		try {
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet hoja = workbook.createSheet();
			int numFila = 0;
			List<Weather> ws = getWeathers();
			for (Weather w : ws) {
				numFila = nuevaFila(hoja, numFila, w);
			}
			File fichero = new File("fichero.xls");
			FileOutputStream ficheroVuelta = new FileOutputStream(fichero);
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
		celdaWeather_Descriptions.setCellValue(w.getWeather_descriptions().get(0));
		HSSFCell celdaWind_Speed = fila.createCell(numCelda++);
		celdaWind_Speed.setCellValue(w.getWind_speed());
		HSSFCell celdaWind_Degree = fila.createCell(numCelda++);
		celdaWind_Degree.setCellValue(w.getWind_degree());
		HSSFCell celdaWind_Direction = fila.createCell(numCelda++);
		celdaWind_Direction.setCellValue(w.getWind_dir());
		HSSFCell celdaPressure = fila.createCell(numCelda++);
		celdaPressure.setCellValue(w.getPressure());
		HSSFCell celdaPrecip = fila.createCell(numCelda++);
		celdaPrecip.setCellValue(w.getPrecip());
		HSSFCell celdaHumidity = fila.createCell(numCelda++);
		celdaHumidity.setCellValue(w.getHumidity());
		HSSFCell celdaCity = fila.createCell(numCelda++);
		celdaCity.setCellValue(w.getCity());
		return numFila;
	}

	@Override
	public Weather crearWeatherDeUnJSON(String q) throws CustomException {
		PropertyUtil pu = new PropertyUtil(r);
		String jsonString = rest.getForObject(pu.getPropiedad("ApiUrl") + q, String.class);
		Weather weather = null;
		Gson gson = new Gson();
		try {
			JSONObject json = new JSONObject(jsonString).getJSONObject("current");
			weather = gson.fromJson(json.toString(), Weather.class);
			weather.setCity(new JSONObject(jsonString).getJSONObject("location").getString("name"));
			weather = nuevoWeather(weather);
			return weather;
		} catch (JSONException e) {
			CustomException ce = new CustomException("Error en el JSON: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
	}

	@Override
	public String ficheroBase64() throws CustomException{
		File fichero = new File("fichero.xls");
		try {
			@SuppressWarnings("resource")
			FileInputStream in = new FileInputStream(fichero);
			byte[] bytes = new byte[(int)fichero.length()];
			in.read(bytes);
			String ficheroDecodificado = Base64.getEncoder().encodeToString(bytes);
			return ficheroDecodificado;
		} catch (Exception e) {
			CustomException ce = new CustomException("Error al exportar el xls: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
	}
}
