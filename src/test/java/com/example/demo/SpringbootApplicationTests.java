package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.EmpDeptoDAO;
import com.example.demo.dao.WeatherDAO;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.Weather;
import com.example.demo.service.EmpDeptoService;
import com.example.demo.service.EmpDeptoServiceImpl;
import com.example.demo.service.WeatherService;
import com.example.demo.service.WeatherServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SpringbootApplicationTests {

	@InjectMocks
	private WeatherService weatherService = new WeatherServiceImpl();
	
	@InjectMocks
	private EmpDeptoService empDeptoService = new EmpDeptoServiceImpl();
	
	@Mock
	private WeatherDAO weatherDao;
	
	@Mock
	private EmpDeptoDAO empDeptoDAO;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void getWeathers() throws CustomException {
		when(weatherDao.getWeathers()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), weatherService.getWeathers());
	}
}
