package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Departamento;
import com.example.demo.modelo.Empleado;
import com.example.demo.service.EmpDeptoService;

@RestController
public class EmpDeptoController {

	@Autowired
	private EmpDeptoService service;

	@GetMapping("/empleados")
	public ResponseEntity<List<Empleado>> getEmpleados() throws CustomException{
		List<Empleado> empleados = service.getEmpleados();
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
		List<Departamento> deptos = service.getDepartamento();
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
		List<Empleado> secretarios = service.getSecretarios();
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
		List<Empleado> empleados = service.getNomSal();
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
		List<Empleado> empleados = service.getEmpleadosOrd();
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
		List<Departamento> deptos = service.getNombreDepartamento();
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
		List<Empleado> empleados = service.getNomCargo();
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
		List<Empleado> empleados = service.getSalCom2000();
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
		List<Empleado> empleados = service.getComisiones();
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
}
