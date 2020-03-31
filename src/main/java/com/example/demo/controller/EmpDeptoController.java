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

import io.swagger.annotations.ApiOperation;

@RestController
public class EmpDeptoController {

	@Autowired
	private EmpDeptoService empDeptoService;

	@ApiOperation(value="Devuelve los empleados")
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
	
	@ApiOperation(value="Devuelve los departamentos")
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
	
	@ApiOperation(value="Devuelve los secretarios")
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
	
	@ApiOperation(value="Devuelve el nombre y salario de los empleados")
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
	
	@ApiOperation(value="Devuelve los empleados en orden")
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
	
	@ApiOperation(value="Devuelve el nombre de los empleados")
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
	
	@ApiOperation("Devuelve el nombre y cargo del empleado")
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
	
	@ApiOperation(value="Devuelve salario y comisión de los empleados del dpto. 2000")
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
	
	@ApiOperation("Devuelve las comisiones del empleado")
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
	
}
