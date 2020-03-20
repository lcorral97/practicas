package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmpDeptoDAO;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Departamento;
import com.example.demo.modelo.Empleado;
import com.example.demo.service.EmpDeptoService;

@Service
public class EmpDeptoServiceImpl implements EmpDeptoService{

	@Autowired
	private EmpDeptoDAO dao;

	@Override
	public List<Empleado> getEmpleados() throws CustomException{
		return dao.getEmpleados();
	}

	@Override
	public List<Departamento> getDepartamento() throws CustomException{
		return dao.getDepartamento();
	}

	@Override
	public List<Empleado> getSecretarios() throws CustomException{
		return dao.getSecretarios();
	}

	@Override
	public List<Empleado> getNomSal() throws CustomException{
		return dao.getNomSal();
	}

	@Override
	public List<Empleado> getEmpleadosOrd() throws CustomException{
		return dao.getEmpleadosOrd();
	}

	@Override
	public List<Departamento> getNombreDepartamento() throws CustomException{
		return dao.getNombreDepartamento();
	}

	@Override
	public List<Empleado> getNomCargo() throws CustomException{
		return dao.getNomCargo();
	}

	@Override
	public List<Empleado> getSalCom2000() throws CustomException{
		return dao.getSalCom2000();
	}

	@Override
	public List<Empleado> getComisiones() throws CustomException{
		return dao.getComisiones();
	}
}
