package com.example.demo.daoImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.SpringbootApplication;
import com.example.demo.config.ConexionConfig;
import com.example.demo.dao.EmpDeptoDAO;
import com.example.demo.exception.CustomException;
import com.example.demo.modelo.Departamento;
import com.example.demo.modelo.Empleado;

@Repository
public class EmpDeptoDAOImpl implements EmpDeptoDAO {

	//@Autowired
	//public DataSource derbyDataSource
	
	@Autowired
	public ConexionConfig config;

	@Override
	public List<Empleado> getEmpleados() throws CustomException {
		ResultSet rst;
		List<Empleado> empleados = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement().executeQuery("Select * From empleados");
			if (rst != null) {
				/*ResultSetMetaData rsmd = rst.getMetaData();
				int i = rsmd.getColumnCount();
				for (int j = 1; j <= i; j++) {
					System.out.println(rsmd.getColumnName(j));
				}*/
				//Mirar los campos del rst
				empleados = new ArrayList<>();
				while (rst.next()) {
					empleados.add(crearEmpleado(rst));
				}
			}
			rst.close();
			config.getH2DataSource().getConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return empleados;
	}

	@Override
	public List<Departamento> getDepartamento() throws CustomException {
		ResultSet rst;
		List<Departamento> deptos = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement().executeQuery("Select * From Departamentos");
			if (rst != null) {
				deptos = new ArrayList<>();
				while (rst.next()) {
					deptos.add(crearDepartamento(rst));
				}
			}
			rst.close();
			config.getH2DataSource().getConnection().close();
		} catch (SQLException e) {
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return deptos;
	}

	@Override
	public List<Empleado> getSecretarios() throws CustomException {
		ResultSet rst;
		List<Empleado> secretarios = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement()
					.executeQuery("Select * From Empleados Where Lower(cargoE)='secretaria'");
			if (rst != null) {
				secretarios = new ArrayList<>();
				while (rst.next()) {
					secretarios.add(crearEmpleado(rst));
				}
			}
			rst.close();
			config.getH2DataSource().getConnection().close();
		} catch (SQLException e) {
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return secretarios;
	}

	@Override
	public List<Empleado> getNomSal() throws CustomException {
		ResultSet rst;
		List<Empleado> empleados = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement()
					.executeQuery("Select nomEmp,salEmp From Empleados");
			if (rst != null) {
				empleados = new ArrayList<>();
				while (rst.next()) {
					empleados.add((Empleado) crear(new Empleado(), "NomEmp,SalEmp", rst, "String,Double"));
				}
			}
			config.getH2DataSource().getConnection().close();
			rst.close();
		} catch (SQLException e) {
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return empleados;
	}

	@Override
	public List<Empleado> getEmpleadosOrd() throws CustomException {
		ResultSet rst;
		List<Empleado> empleados = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement()
					.executeQuery("Select * From Empleados Order By nomEmp");
			if (rst != null) {
				empleados = new ArrayList<>();
				while (rst.next()) {
					empleados.add(crearEmpleado(rst));
				}
			}
			rst.close();
			config.getH2DataSource().getConnection().close();
		} catch (SQLException e) {
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return empleados;
	}

	@Override
	public List<Departamento> getNombreDepartamento() throws CustomException {
		ResultSet rst;
		List<Departamento> deptos = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement()
					.executeQuery("Select nombreDepto From Departamentos");
			if (rst != null) {
				deptos = new ArrayList<>();
				while (rst.next()) {
					deptos.add((Departamento) crear(new Departamento(), "NombreDepto", rst, "String"));
				}
			}
			rst.close();
			config.getH2DataSource().getConnection().close();
		} catch (SQLException e) {
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return deptos;
	}

	@Override
	public List<Empleado> getNomCargo() throws CustomException {
		ResultSet rst;
		List<Empleado> empleados = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement()
					.executeQuery("Select nomEmp,cargoE From Empleados Order By salEmp");
			if (rst != null) {
				empleados = new ArrayList<>();
				while (rst.next()) {
					empleados.add((Empleado) crear(new Empleado(), "NomEmp,CargoE", rst, "String,String"));
				}
			}
			rst.close();
			config.getH2DataSource().getConnection().close();
		} catch (SQLException e) {
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return empleados;
	}

	@Override
	public List<Empleado> getSalCom2000() throws CustomException {
		ResultSet rst;
		List<Empleado> empleados = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement()
					.executeQuery("Select salEmp,comisionE From Empleados Where codDepto='2000' Order By comisionE");
			if (rst != null) {
				empleados = new ArrayList<>();
				while (rst.next()) {
					empleados.add((Empleado) crear(new Empleado(), "SalEmp,ComisionE", rst, "Double,Double"));
				}
			}
			rst.close();
			config.getH2DataSource().getConnection().close();
		} catch (SQLException e) {
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return empleados;
	}

	@Override
	public List<Empleado> getComisiones() throws CustomException {
		ResultSet rst;
		List<Empleado> empleados = null;
		try {
			rst = config.getH2DataSource().getConnection().createStatement().executeQuery("Select comisionE From Empleados");
			if (rst != null) {
				empleados = new ArrayList<>();
				while (rst.next()) {
					empleados.add((Empleado) crear(new Empleado(), "ComisionE", rst, "Double"));
				}
			}
			rst.close();
			config.getH2DataSource().getConnection().close();
		} catch (SQLException e) {
			CustomException ce = new CustomException("Fallo en la creación de la query: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return empleados;
	}

	// Método para crear un empleado a través de un ResultSet
	private Empleado crearEmpleado(ResultSet rst) throws CustomException {
		Empleado e = new Empleado();
		try {
			e.setNDIEmp(rst.getString("nDIEmp"));
			e.setNomEmp(rst.getString("nomEmp"));
			e.setSexEmp(rst.getString("sexEmp").charAt(0));
			e.setFecNac(rst.getDate("fecNac"));
			e.setFecIncorporacion(rst.getDate("fecIncorporacion"));
			e.setSalEmp(rst.getDouble("salEmp"));
			e.setCargoE(rst.getString("cargoE"));
			e.setJefeId(rst.getString("jefeID"));
			e.setCodDepto(rst.getString("codDepto"));
			e.setComisionE(rst.getDouble("comisionE"));
			e.setPassword(rst.getString("password"));
			e.setCiudad(rst.getString("ciudad"));
		} catch (SQLException e1) {
			e1.printStackTrace();
			CustomException ce = new CustomException("No se pudo crear el empleado: " + e1);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return e;
	}

	// Método para crear un departamento mediante un ResultSet
	private Departamento crearDepartamento(ResultSet rst) throws CustomException {
		Departamento d = new Departamento();
		try {
			d.setCodDepto(rst.getString("codDepto"));
			d.setNombreDepto(rst.getString("nombreDepto"));
			d.setCiudad(rst.getString("ciudad"));
			d.setCodDirector(rst.getString("codDirector"));
		} catch (SQLException e) {
			CustomException ce = new CustomException("No se pudo crear el departamento: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
		return d;
	}

	// Sobrecarga del método crearEmpleado, sólo mete los campos pedidos
	/* private Empleado crearEmpleado(ResultSet rst, String campos) throws
	 * SQLException { String[] campo = campos.split(","); Empleado e = new
	 * Empleado(); for(int i=0; i < campo.length; i++) {
	 * System.out.println(campo[i]); switch (campo[i].toUpperCase()) { case
	 * "NDIEMP": e.setNDIEmp(rst.getString("nDIEmp")); break; case "NOMEMP":
	 * e.setNomEmp(rst.getString("nomEmp")); break; case "SEXEMP":
	 * e.setSexEmp(rst.getString("sexEmp").charAt(0)); break; case "FECNAC":
	 * e.setFecNac(rst.getDate("fecNac")); break; case "FECINCORPORACION":
	 * e.setFecIncorporacion(rst.getDate("fecIncorporacion")); break; case "SALEMP":
	 * e.setSalEmp(rst.getDouble("salEmp")); break; case "CARGOE":
	 * e.setCargoE(rst.getString("cargoE")); break; case "JEFEID":
	 * e.setJefeId(rst.getString("jefeId")); break; case "CODDEPTO":
	 * e.setCodDepto(rst.getString("codDepto")); break; case "COMISIONE":
	 * e.setComisionE(rst.getDouble("comisionE")); break; } } return e; }
	 */

	// Sobrecarga del método crearDepartamento, sólo mete los campos pedidos
	/* private Departamento crearDepartamento(ResultSet rst, String campos) throws
	 * SQLException { String[] campo = campos.split(","); Departamento d = new
	 * Departamento(); for (int i = 0; i < campo.length; i++) { switch
	 * (campo[i].toUpperCase()) { case "CODDEPTO":
	 * d.setCodDepto(rst.getString("codDepto")); break; case "CIUDAD":
	 * d.setCiudad(rst.getString("ciudad")); break; case "NOMBREDEPTO":
	 * d.setNombreDepto(rst.getString("nombreDepto")); break; case "CODDIRECTOR":
	 * d.setCodDirector(rst.getString("codDirector")); break; } } return d; }
	 */

	// Método para crear cualquier objeto, se mete el objeto, los campos, el
	// resultset, y los tipos de los campos
	private Object crear(Object obj, String campos, ResultSet rst, String tipos) throws CustomException {
		if (campos != null && tipos != null) {
			String[] campo = campos.split(",");
			String[] tipo = tipos.split(",");
			/*
			 * Dividimos los campos y sus respectivos tipos, éstos han sido previemente
			 * introducidos con el siguiente formato: "campo1,campo2,...,campoN" y
			 * "tipo1,tipo2,...,tipoN" (LA PRIMERA LETRA SIEMPRE EN MAYÚSCULA)
			 */
			Field[] fs = obj.getClass().getDeclaredFields();
			/*
			 * Sacamos los campos de la clase, en este caso sólo puede ser, o bien un
			 * empleado, o bien un departamento.
			 */
			for (int i = 0; i < campo.length; i++) {
				/*
				 * Bucle sobre cada uno de los campos que queremos sacar.
				 */
				for (int j = 0; j < fs.length; j++) {
					/*
					 * Bucle sobre cada campo de la clase.
					 */
					if (campo[i].equalsIgnoreCase(fs[j].getName())) {
						/*
						 * Sólo accede si los campos se llaman iguales
						 */
						cargarMetodosYCampos(obj, rst, campo, tipo, fs, i, j);
					}
				}
			}
		}
		return obj;
	}

	private void cargarMetodosYCampos(Object obj, ResultSet rst, String[] campo, String[] tipo, Field[] fs, int i,
			int j) throws CustomException {
		try {
			Method mSet = obj.getClass().getMethod("set" + campo[i] /* Nombre del método */,
					fs[j].getType() /* Tipo del parámetro de la función (se pueden poner tantos como se quiera) */);
			Method mGet = null;
			if (tipo[i].equals("Char")) {
				mGet = ResultSet.class.getMethod("getString", String.class);
			} else {
				mGet = ResultSet.class.getMethod("get" + tipo[i], String.class);
			}
			/*
			 * Sacamos los métodos set del modelo y los métodos get del ResultSet. Debido a
			 * que no existe un método en el ResultSet que nos saque sólo un carácter,
			 * tenemos que usar getString()
			 */
			try {
				if (tipo[i].equals("Char")) {
					mSet.invoke(obj, ((String) mGet.invoke(rst, campo[i])).charAt(0));
				} else {
					mSet.invoke(obj/* Objeto sobre el que se realiza */,
							mGet.invoke(rst, campo[i])/* Parámetro usado para la función */);
				}
				/*
				 * Invocamos el método. Cómo puede haber casos donde necesitemos un carácter,
				 * usamos, como previamente se ha explicado, el método getString(). Para
				 * transformarlo en Char, una vez se haya invocado se hace un casting a String y
				 * se usa el método de String .charAt(0)
				 */
			} catch (IllegalAccessException e) {
				CustomException ce = new CustomException(e.toString());
				LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
				throw ce;
			} catch (IllegalArgumentException e) {
				CustomException ce = new CustomException(e.toString());
				LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
				throw ce;
			} catch (InvocationTargetException e) {
				CustomException ce = new CustomException(e.toString());
				LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
				throw ce;
			}
		} catch (NoSuchMethodException e) {
			CustomException ce = new CustomException("Método no encontrado: " + e);
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		} catch (SecurityException e) {
			CustomException ce = new CustomException(e.toString());
			LoggerFactory.getLogger(SpringbootApplication.class).warn(ce.getMessage());
			throw ce;
		}
	}
}
