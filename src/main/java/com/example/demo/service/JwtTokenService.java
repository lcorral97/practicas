package com.example.demo.service;

import com.example.demo.exception.CustomException;

public interface JwtTokenService {

	String crearJwtToken(String empId);
	boolean comprobarToken(String jwt) throws CustomException;
	
}
