package com.example.demo.dao;
import java.util.List;

import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Departamento;
import com.example.demo.modelo.Empleado;

public interface EmpDeptoDAO {

	//Lista compleata de empleados
	List<Empleado> getEmpleados() throws CustomException;
	//Lista completa de departamentos
	List<Departamento> getDepartamento() throws CustomException;
	//Lista de empleados que sean secretarios/as
	List<Empleado> getSecretarios() throws CustomException;
	//Lista de empleados (sólo nombre y salario)
	List<Empleado> getNomSal() throws CustomException;
	//Lista completa de empleados, ordenado por nombre
	List<Empleado> getEmpleadosOrd() throws CustomException;
	//Lista de departamentos (sólo nombre)
	List<Departamento> getNombreDepartamento() throws CustomException;
	//Lista de empleados, ordenado por salario (sólo nombre y cargo)
	List<Empleado> getNomCargo() throws CustomException;
	//Lista de empleados del departamento 2000, ordenado por comisión (sólo salario y comisión)
	List<Empleado> getSalCom2000() throws CustomException;
	//Lista de comisiones
	List<Empleado> getComisiones() throws CustomException;
	
}
