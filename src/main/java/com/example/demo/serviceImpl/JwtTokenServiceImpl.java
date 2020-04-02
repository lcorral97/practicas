package com.example.demo.serviceImpl;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Empleado;
import com.example.demo.service.EmpDeptoService;
import com.example.demo.service.JwtTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

	private String keyStr = "clave_random";
	
	private final long TIEMPO_EXP = 863000000;
	
	@Autowired
	private EmpDeptoService empDeptoService;
	
	@Override
	public String crearJwtToken(String empId) {
		
		SignatureAlgorithm sa = SignatureAlgorithm.HS256;
		
		byte[] keyBytes = keyStr.getBytes();
		Key key = new SecretKeySpec(keyBytes, sa.getJcaName());
		// Pasamos la clave a bytes y creamos una clave con los bites
		// y el nombre del algoritmo.
		JwtBuilder jwtb = Jwts.builder()
							.setIssuedAt(new Date(System.currentTimeMillis()))
							.setIssuer(empId)
							.setExpiration(new Date(System.currentTimeMillis() + TIEMPO_EXP))
							.signWith(sa, key);
		return jwtb.compact();
	}

	@Override
	public boolean comprobarToken(String jwt) throws CustomException {
		Claims c = Jwts.parser()
					.setSigningKey(keyStr.getBytes())
					.parseClaimsJwt(jwt)
					.getBody();
		return isAValidToken(c);
	}

	private boolean isAValidToken(Claims c) throws CustomException {
		boolean valido = false;
		if (c != null) {
			if (System.currentTimeMillis() < c.getExpiration().getTime() &&
					System.currentTimeMillis() > c.getIssuedAt().getTime()) {
				for (Empleado e: empDeptoService.getEmpleados()) {
					if (e.getNDIEmp().equals(c.getIssuer())) {
						valido = true;
					}
				}
			}
		}
		return valido;
	}

}
