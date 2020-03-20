package com.example.demo.config;

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

	//@Bean(name = "derbyDataSource")
	public DataSource getH2DataSource() throws CustomException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		PropertyUtil pu = new PropertyUtil("src/main/resources/config.properties");
		try {
			dataSource.setDriverClassName(pu.getDriver());
			dataSource.setUsername(pu.getUsername());
			dataSource.setPassword(pu.getPassword());
			dataSource.setUrl(pu.getUrl());
			if (!dataSource.getUsername().equals(pu.getUsername()) ||
					!dataSource.getPassword().equals(pu.getPassword())) {
				throw new CustomException("Usuario / Password de la DB incorrecta");
			}
		} catch (Exception e) {
			CustomException ce = new CustomException("Se ha producido un error al obtener el Datasource: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class)
					.warn(ce.getMessage());
			throw ce;
		}
		return dataSource;
	}

}
