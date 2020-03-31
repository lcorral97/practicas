package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.example.demo.SpringbootApplication;
import com.example.demo.exception.CustomException;

public class ApiPropertyUtil {

	private Properties propiedades = new Properties();
	
	public ApiPropertyUtil(Resource r) throws CustomException {
		try {
			InputStream is = r.getInputStream();
			propiedades.load(is);
		} catch (IOException e) {
			CustomException ce = new CustomException("No se pueden cargar las propiedades del api: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
	}
	
	public String getPropiedad(String prop) {
		return propiedades.getProperty(prop);
	}
}
