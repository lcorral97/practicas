package com.example.demo.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Empleado;
import com.example.demo.service.EmpDeptoService;

@RestController
public class LoginController {
	
	@Autowired
	private EmpDeptoService empDeptoService;
	
	@GetMapping("/login")
	private ResponseEntity<Empleado> login(@RequestHeader("Authorization") String auth) throws CustomException{
		List<Empleado> emps = empDeptoService.getEmpleados();
		Empleado empCorrecto = null;
		for (Empleado e : emps) {
			String empCod = "Basic " + Base64.getEncoder().encodeToString((e.getNDIEmp() +":" + e.getPassword()).getBytes());
			if (empCod.equals(auth)) {
				empCorrecto = e;
			}
		}
		if (empCorrecto == null) {
			return new ResponseEntity<>(empCorrecto, HttpStatus.UNAUTHORIZED);
		} else {
			return ResponseEntity.ok(empCorrecto);
		}
	}

}
