package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.SpringbootApplication;
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
}
