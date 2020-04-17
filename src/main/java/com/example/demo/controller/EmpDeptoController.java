package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Departamento;
import com.example.demo.modelo.Empleado;
import com.example.demo.service.EmpDeptoService;
import com.example.demo.service.JwtTokenService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class EmpDeptoController {

	@Autowired
	private EmpDeptoService empDeptoService;
	
	@Autowired
	private JwtTokenService jwtTokenService;

	@ApiOperation(value="Devuelve los empleados")
	@GetMapping("/empleados")
	public ResponseEntity<List<Empleado>> getEmpleados(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Empleado> empleados = empDeptoService.getEmpleados();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (empleados == null) {
				return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
			} else {
				if (empleados.isEmpty()) {
					return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT); //No devuelve nada, sólo indica que la petición es válida, pero no ha encontrado ningún resultado...
				} else {
					return ResponseEntity.ok(empleados);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value="Devuelve los departamentos")
	@GetMapping("/departamentos")
	public ResponseEntity<List<Departamento>> getDepartamentos(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Departamento> deptos = empDeptoService.getDepartamento();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (deptos == null) {
				return new ResponseEntity<>(deptos,HttpStatus.NOT_FOUND);
			} else {
				if (deptos.isEmpty()) {
					return new ResponseEntity<>(deptos, HttpStatus.NO_CONTENT); 
				} else {
					return ResponseEntity.ok(deptos);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value="Devuelve los secretarios")
	@GetMapping("/secretarios")
	public ResponseEntity<List<Empleado>> getSecretarios(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Empleado> secretarios = empDeptoService.getSecretarios();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (secretarios == null) {
				return new ResponseEntity<>(secretarios,HttpStatus.NOT_FOUND);
			} else {
				if (secretarios.isEmpty()) {
					return new ResponseEntity<>(secretarios, HttpStatus.NO_CONTENT);
				} else {
					return ResponseEntity.ok(secretarios);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value="Devuelve el nombre y salario de los empleados")
	@GetMapping("/empNomSal")
	public ResponseEntity<List<Empleado>> getNomSal(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Empleado> empleados = empDeptoService.getNomSal();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (empleados == null) {
				return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
			} else {
				if (empleados.isEmpty()) {
					return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
				} else {
					return ResponseEntity.ok(empleados);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value="Devuelve los empleados en orden")
	@GetMapping("/empleadosOrdenados")
	public ResponseEntity<List<Empleado>> getEmpleadosOrd(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Empleado> empleados = empDeptoService.getEmpleadosOrd();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (empleados == null) {
				return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
			} else {
				if (empleados.isEmpty()) {
					return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
				} else {
					return ResponseEntity.ok(empleados);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value="Devuelve el nombre de los empleados")
	@GetMapping("/nombreDepto")
	public ResponseEntity<List<Departamento>> getNombreDepartamento(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Departamento> deptos = empDeptoService.getNombreDepartamento();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (deptos == null) {
				return new ResponseEntity<>(deptos, HttpStatus.NOT_FOUND);
			} else {
				if (deptos.isEmpty()) {
					return new ResponseEntity<>(deptos, HttpStatus.NO_CONTENT);
				} else {
					return ResponseEntity.ok(deptos);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation("Devuelve el nombre y cargo del empleado")
	@GetMapping("/empNomCargo")
	public ResponseEntity<List<Empleado>> getNomCargo(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Empleado> empleados = empDeptoService.getNomCargo();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (empleados == null) {
				return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
			} else {
				if (empleados.isEmpty()) {
					return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
				} else {
					return ResponseEntity.ok(empleados);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value="Devuelve salario y comisión de los empleados del dpto. 2000")
	@GetMapping("/empSalCom2000")
	public ResponseEntity<List<Empleado>> getSalCom2000(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Empleado> empleados = empDeptoService.getSalCom2000();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (empleados == null) {
				return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
			} else {
				if (empleados.isEmpty()) {
					return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
				} else {
					return ResponseEntity.ok(empleados);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value="Devuelve las comisiones del empleado")
	@GetMapping("/comisiones")
	public ResponseEntity<List<Empleado>> getComisiones(@RequestHeader("Authorization") String jwtToken) throws CustomException{
		List<Empleado> empleados = empDeptoService.getComisiones();
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (empleados == null) {
				return new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);
			} else {
				if (empleados.isEmpty()) {
					return new ResponseEntity<>(empleados, HttpStatus.NO_CONTENT);
				} else {
					return ResponseEntity.ok(empleados);
				}
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value="Devuelve un empleado con un id")
	@GetMapping("/empleado")
	private ResponseEntity<Empleado> getEmpleado(@RequestParam(name="id", required=true) String id,
			@RequestHeader("Authorization") String jwtToken) throws CustomException{
		Empleado e = empDeptoService.getEmpleado(id);
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (e == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {
				return ResponseEntity.ok(e);
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation("Devuelve un Departamento con un id")
	@GetMapping("/departamento")
	private ResponseEntity<Departamento> getDepartamento(@RequestParam(name="id", required=true) String id,
			@RequestHeader("Authorization") String jwtToken) throws CustomException{
		Departamento d = empDeptoService.getDepartamento(id);
		if (jwtTokenService.comprobarToken(jwtToken)) {
			if (d == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {
				return ResponseEntity.ok(d);
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
}
