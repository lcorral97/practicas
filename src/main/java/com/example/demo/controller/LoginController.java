package com.example.demo.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Empleado;
import com.example.demo.service.EmpDeptoService;
import com.example.demo.service.JwtTokenService;

import io.swagger.annotations.ApiOperation;

@RestController
public class LoginController {
	
	@Autowired
	private EmpDeptoService empDeptoService;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@GetMapping("/login")
	@ApiOperation(value="Login. SE NECESITA AUTORIZACIÓN")
	private ResponseEntity<String> login(@RequestHeader("Authorization") String auth) throws CustomException{
		Empleado empCorrecto = empDeptoService.login(auth);
		String[] userPass = new String[2];
		if (auth.split(" ").length == 2) {
			byte[] byteArray = Base64.decodeBase64(auth.split(" ")[1].getBytes());
			userPass = new String(byteArray).split(":");
		}
		if (empCorrecto == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		} else {
			if (userPass[1].equals(empCorrecto.getPassword())) {
				return ResponseEntity.ok(jwtTokenService.crearJwtToken(userPass[0]));
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		}
	}

}
