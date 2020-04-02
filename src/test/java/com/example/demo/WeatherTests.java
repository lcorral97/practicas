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

import com.example.demo.dao.WeatherDAO;
import com.example.demo.exception.CustomException;
import com.example.demo.service.WeatherService;
import com.example.demo.serviceImpl.WeatherServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class WeatherTests {

	@InjectMocks
	private WeatherService weatherService = new WeatherServiceImpl();
	
	@Mock
	private WeatherDAO weatherDao;

	@Test
	public void getWeathersTest() throws CustomException {
		when(weatherDao.getWeathers()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), weatherService.getWeathers());
	}
}
