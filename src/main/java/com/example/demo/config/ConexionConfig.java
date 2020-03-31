package com.example.demo.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import com.example.demo.SpringbootApplication;
import com.example.demo.exception.CustomException;
import com.example.demo.util.PropertyUtil;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConexionConfig {

	private final String RUTA = "config.properties";
	
	@Value("classpath:" + RUTA)
	private Resource rProp;
	
	@Value("classpath:creaciónTablasDptoEmp.sql")
	private Resource ctdesql;
	
	@Value("classpath:creaciónTemporal.sql")
	private Resource ctsql;
	
	//@Bean(name = "derbyDataSource")
	public DataSource getH2DataSource() throws CustomException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		PropertyUtil pu = new PropertyUtil(rProp);
		try {
			dataSource.setDriverClassName(pu.getPropiedad("DriverClassName"));
			dataSource.setUsername(pu.getPropiedad("Username"));
			if (dataSource.getUsername() == null) {
				throw new CustomException("Username nulo");
			}
			dataSource.setPassword(pu.getPropiedad("Password"));
			if (dataSource.getPassword() == null) {
				throw new CustomException("Password nulo");
			}
			dataSource.setUrl(pu.getPropiedad("Url"));
			if (dataSource.getUrl() == null) {
				throw new CustomException("Url nula");
			}
			if (DriverManager.getDriver(dataSource.getConnection().getMetaData().getURL()).getClass().getName() == null) {
				throw new CustomException("Driver nulo");
			}
			crearTablas(dataSource);
			return dataSource;
		} catch (Exception e) {
			CustomException ce = new CustomException("Se ha producido un error al obtener el Datasource: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
					.warn(ce.getMessage());
			throw ce;
		}
	}

	private void crearTablas(DriverManagerDataSource dataSource)
			throws CustomException {
		try {
			Connection cnn = dataSource.getConnection(dataSource.getUsername(), dataSource.getPassword());
			ScriptRunner srn = new ScriptRunner(cnn);
			Reader rde = new BufferedReader(new FileReader(ctdesql.getFile()));
			Reader rt = new BufferedReader(new FileReader(ctsql.getFile()));
			srn.runScript(rde);
			srn.runScript(rt);
		} catch (Exception e) {
			CustomException ce = new CustomException("Error al crear las tablas: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
				.warn(ce.getMessage());
			throw ce;
		}
	}

}
