package com.example.demo.serviceImpl;

import java.util.Base64;
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

	@Override
	public Empleado login(String auth) throws CustomException{
		Empleado empCorrecto = null;
		for (Empleado e : getEmpleados()) {
			String empCod = "Basic " + Base64.getEncoder().encodeToString((e.getNDIEmp() +":" + e.getPassword()).getBytes());
			if (empCod.equals(auth)) {
				empCorrecto = e;
			}
		}
		return empCorrecto;
	}

	@Override
	public Empleado getEmpleado(String idEmp) throws CustomException {
		return dao.getEmpleado(idEmp);
	}

	@Override
	public Departamento getDepartamento(String idDepto) throws CustomException {
		return dao.getDepartamento(idDepto);
	}
}
