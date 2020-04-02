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
import com.example.demo.exception.CustomException;
import com.example.demo.service.EmpDeptoService;
import com.example.demo.serviceImpl.EmpDeptoServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmpDeptoTests {

	@InjectMocks
	private EmpDeptoService empDeptoService = new EmpDeptoServiceImpl();
	
	@Mock
	private EmpDeptoDAO empDeptoDAO;
	
	@Test
	public void getEmpleadosTest() throws CustomException {
		when(empDeptoDAO.getEmpleados()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getEmpleados());
	}
	
	@Test
	public void getDeptosTest() throws CustomException {
		when(empDeptoDAO.getDepartamento()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getDepartamento());
	}
	
	@Test
	public void getSecretariosTest() throws CustomException {
		when(empDeptoDAO.getSecretarios()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getSecretarios());
	}
	
	@Test
	public void getNomSalTest() throws CustomException {
		when(empDeptoDAO.getNomSal()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getNomSal());
	}
	
	@Test
	public void getEmpleadosOrdTest() throws CustomException {
		when(empDeptoDAO.getEmpleadosOrd()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getEmpleadosOrd());
	}
	
	@Test
	public void getNombreDepartamentoTest() throws CustomException {
		when(empDeptoDAO.getNombreDepartamento()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getNombreDepartamento());
	}
	
	@Test
	public void getNomCargoTest() throws CustomException {
		when(empDeptoDAO.getNomCargo()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getNomCargo());
	}
	
	@Test
	public void getSalCom2000Test() throws CustomException {
		when(empDeptoDAO.getSalCom2000()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getSalCom2000());
	}
	
	@Test
	public void getComisionesTest() throws CustomException {
		when(empDeptoDAO.getComisiones()).thenReturn(new ArrayList<>());
		assertEquals(new ArrayList<>(), empDeptoService.getComisiones());
	}
}
