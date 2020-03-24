package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.List;

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
import com.example.demo.modelo.Departamento;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.Weather;
import com.example.demo.service.EmpDeptoService;
import com.example.demo.service.WeatherService;
import com.example.demo.util.ApiPropertyUtil;
import com.google.gson.Gson;

@RestController
public class Controller {

	@Autowired
	private EmpDeptoService empDeptoService;

	@Autowired
	private RestTemplate rest;
	
	@Autowired
	private WeatherService weatherService;
	
	private final String RUTA = "src/main/resources/api.properties";
	
	@GetMapping("/empleados")
	public ResponseEntity<List<Empleado>> getEmpleados() throws CustomException{
		List<Empleado> empleados = empDeptoService.getEmpleados();
		if (empleados == null) {
			return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
		} else {
			if (empleados.isEmpty()) {
				return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT); //No devuelve nada, sólo indica que la petición es válida, pero no ha encontrado ningún resultado...
			} else {
				return ResponseEntity.ok(empleados);
			}
		}
	}
	
	@GetMapping("/departamentos")
	public ResponseEntity<List<Departamento>> getDepartamentos() throws CustomException{
		List<Departamento> deptos = empDeptoService.getDepartamento();
		if (deptos == null) {
			return new ResponseEntity<>(deptos,HttpStatus.NOT_FOUND);
		} else {
			if (deptos.isEmpty()) {
				return new ResponseEntity<>(deptos, HttpStatus.NO_CONTENT); 
			} else {
				return ResponseEntity.ok(deptos);
			}
		}
	}
	
	@GetMapping("/secretarios")
	public ResponseEntity<List<Empleado>> getSecretarios() throws CustomException{
		List<Empleado> secretarios = empDeptoService.getSecretarios();
		if (secretarios == null) {
			return new ResponseEntity<>(secretarios,HttpStatus.NOT_FOUND);
		} else {
			if (secretarios.isEmpty()) {
				return new ResponseEntity<>(secretarios, HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.ok(secretarios);
			}
		}
	}
	
	@GetMapping("/empNomSal")
	public ResponseEntity<List<Empleado>> getNomSal() throws CustomException{
		List<Empleado> empleados = empDeptoService.getNomSal();
		if (empleados == null) {
			return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
		} else {
			if (empleados.isEmpty()) {
				return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.ok(empleados);
			}
		}
	}
	
	@GetMapping("/empleadosOrdenados")
	public ResponseEntity<List<Empleado>> getEmpleadosOrd() throws CustomException{
		List<Empleado> empleados = empDeptoService.getEmpleadosOrd();
		if (empleados == null) {
			return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
		} else {
			if (empleados.isEmpty()) {
				return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.ok(empleados);
			}
		}
	}
	
	@GetMapping("/nombreDepto")
	public ResponseEntity<List<Departamento>> getNombreDepartamento() throws CustomException{
		List<Departamento> deptos = empDeptoService.getNombreDepartamento();
		if (deptos == null) {
			return new ResponseEntity<>(deptos, HttpStatus.NOT_FOUND);
		} else {
			if (deptos.isEmpty()) {
				return new ResponseEntity<>(deptos, HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.ok(deptos);
			}
		}
	}
	
	@GetMapping("/empNomCargo")
	public ResponseEntity<List<Empleado>> getNomCargo() throws CustomException{
		List<Empleado> empleados = empDeptoService.getNomCargo();
		if (empleados == null) {
			return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
		} else {
			if (empleados.isEmpty()) {
				return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.ok(empleados);
			}
		}
	}
	
	@GetMapping("/empSalCom2000")
	public ResponseEntity<List<Empleado>> getSalCom2000() throws CustomException{
		List<Empleado> empleados = empDeptoService.getSalCom2000();
		if (empleados == null) {
			return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
		} else {
			if (empleados.isEmpty()) {
				return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.ok(empleados);
			}
		}
	}
	
	@GetMapping("/comisiones")
	public ResponseEntity<List<Empleado>> getComisiones() throws CustomException{
		List<Empleado> empleados = empDeptoService.getComisiones();
		if (empleados == null) {
			return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
		} else {
			if (empleados.isEmpty()) {
				return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.ok(empleados);
			}
		}
	}
	
	@GetMapping("/weather")
	public ResponseEntity<Weather> nuevoWeather() throws CustomException{
		ApiPropertyUtil apu = new ApiPropertyUtil(RUTA);
		String jsonString = rest.getForObject(apu.getPropiedad("Url"), String.class);
		Weather w = null;
		Gson gson = new Gson();
		try {
			JSONObject json = new JSONObject(jsonString).getJSONObject("current");
			w = gson.fromJson(json.toString(), Weather.class);
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
	
	@GetMapping("/datosExcel")
	public ResponseEntity<String> crearNuevoExcel() throws CustomException{
		weatherService.crearNuevoExcel();
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
}
