package com.example.demo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import com.example.demo.SpringbootApplication;
import com.example.demo.exception.CustomException;

public class PropertyUtil {

	private Properties propiedades = new Properties();

	public PropertyUtil(String config) throws CustomException {
		try {
			InputStream str = new FileInputStream(config);
			this.propiedades.load(str);
			/*
			 * Carga las propiedades de un fichero. La ruta de dicho fichero debe ser
			 * introducida al crear un nuevo objeto del tipo PropertyUtil
			 */
		} catch (IOException e) {
			CustomException ce = new CustomException("No se pueden cargar las propiedades: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
	}
	
	public String getPropiedad(String prop) {
		return propiedades.getProperty(prop);
	}

	/*public String getUrl() {
		return propiedades.getProperty("Url");
	}

	public String getUsername() {
		return propiedades.getProperty("Username");
	}

	public String getPassword() {
		return propiedades.getProperty("Password");
	}

	public String getDriver() {
		return propiedades.getProperty("DriverClassName");
	}*/

}
