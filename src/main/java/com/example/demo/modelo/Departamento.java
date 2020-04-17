package com.example.demo.modelo;

//import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Departamento {

	private String codDepto;
	private String nombreDepto;
	private String ciudad;
	private String codDirector; //private Empleado director;
	
	public String getCodDepto() {
		return codDepto;
	}
	
	public void setCodDepto(String codDepto) {
		this.codDepto = codDepto;
	}
	
	public String getNombreDepto() {
		return nombreDepto;
	}
	
	public void setNombreDepto(String nombreDepto) {
		this.nombreDepto = nombreDepto;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getCodDirector() {
		return codDirector;
	}
	
	public void setCodDirector(String codDirector) {
		this.codDirector = codDirector;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((codDepto == null) ? 0 : codDepto.hashCode());
		result = prime * result + ((codDirector == null) ? 0 : codDirector.hashCode());
		result = prime * result + ((nombreDepto == null) ? 0 : nombreDepto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (codDepto == null) {
			if (other.codDepto != null)
				return false;
		} else if (!codDepto.equals(other.codDepto))
			return false;
		if (codDirector == null) {
			if (other.codDirector != null)
				return false;
		} else if (!codDirector.equals(other.codDirector))
			return false;
		if (nombreDepto == null) {
			if (other.nombreDepto != null)
				return false;
		} else if (!nombreDepto.equals(other.nombreDepto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Departamento [codDepto=" + codDepto + ", nombreDepto=" + nombreDepto + ", ciudad=" + ciudad
				+ ", codDirector=" + codDirector + "]";
	}

	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
