package com.example.demo.config;

import java.sql.DriverManager;

import javax.sql.DataSource;

import com.example.demo.SpringbootApplication;
import com.example.demo.exception.CustomException;
import com.example.demo.util.PropertyUtil;

import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConexionConfig {

	private final String RUTA = "src/main/resources/config.properties";
	
	//@Bean(name = "derbyDataSource")
	public DataSource getH2DataSource() throws CustomException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		PropertyUtil pu = new PropertyUtil(RUTA);
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
			if (DriverManager.getDriver(dataSource.getConnection().getMetaData().getURL()).getClass().getName() != null) {
				throw new CustomException("Driver nulo");
			}
			return dataSource;
		} catch (Exception e) {
			CustomException ce = new CustomException("Se ha producido un error al obtener el Datasource: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
					.warn(ce.getMessage());
			throw ce;
		}
	}

}
