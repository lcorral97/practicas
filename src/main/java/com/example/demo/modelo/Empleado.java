package com.example.demo.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Empleado {

	private String nDIEmp;
	private String nomEmp;
	private char sexEmp;
	private Date fecNac;
	private Date fecIncorporacion;
	private double salEmp;
	private String cargoE;
	private String jefeId;    //private Empleado jefe;
	private String codDepto;  //private Departamento depto;
	private double comisionE; //private List<Empleado> empleadosSub;
	
	public String getNDIEmp() {
		return nDIEmp;
	}
	
	public void setNDIEmp(String nDIEmp) {
		this.nDIEmp = nDIEmp;
	}
	
	public String getNomEmp() {
		return nomEmp;
	}
	
	public void setNomEmp(String nomEmp) {
		this.nomEmp = nomEmp;
	}
	
	public char getSexEmp() {
		return sexEmp;
	}
	
	public void setSexEmp(char sexEmp) {
		this.sexEmp = sexEmp;
	}
	
	public Date getFecNac() {
		return fecNac;
	}
	
	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}
	
	public Date getFecIncorporacion() {
		return fecIncorporacion;
	}
	
	public void setFecIncorporacion(Date fecIncorporacion) {
		this.fecIncorporacion = fecIncorporacion;
	}
	
	public double getSalEmp() {
		return salEmp;
	}
	
	public void setSalEmp(double salEmp) {
		this.salEmp = salEmp;
	}
	
	public String getCargoE() {
		return cargoE;
	}
	
	public void setCargoE(String cargoE) {
		this.cargoE = cargoE;
	}
	
	public String getJefeId() {
		return jefeId;
	}
	
	public void setJefeId(String jefeId) {
		this.jefeId = jefeId;
	}
	
	public String getCodDepto() {
		return codDepto;
	}
	
	public void setCodDepto(String codDepto) {
		this.codDepto = codDepto;
	}
	
	public double getComisionE() {
		return this.comisionE;
	}
	
	public void setComisionE(double comisionE) {
		this.comisionE = comisionE;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargoE == null) ? 0 : cargoE.hashCode());
		result = prime * result + ((codDepto == null) ? 0 : codDepto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(comisionE);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fecIncorporacion == null) ? 0 : fecIncorporacion.hashCode());
		result = prime * result + ((fecNac == null) ? 0 : fecNac.hashCode());
		result = prime * result + ((jefeId == null) ? 0 : jefeId.hashCode());
		result = prime * result + ((nDIEmp == null) ? 0 : nDIEmp.hashCode());
		result = prime * result + ((nomEmp == null) ? 0 : nomEmp.hashCode());
		temp = Double.doubleToLongBits(salEmp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sexEmp;
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
		Empleado other = (Empleado) obj;
		if (cargoE == null) {
			if (other.cargoE != null)
				return false;
		} else if (!cargoE.equals(other.cargoE))
			return false;
		if (codDepto == null) {
			if (other.codDepto != null)
				return false;
		} else if (!codDepto.equals(other.codDepto))
			return false;
		if (Double.doubleToLongBits(comisionE) != Double.doubleToLongBits(other.comisionE))
			return false;
		if (fecIncorporacion == null) {
			if (other.fecIncorporacion != null)
				return false;
		} else if (!fecIncorporacion.equals(other.fecIncorporacion))
			return false;
		if (fecNac == null) {
			if (other.fecNac != null)
				return false;
		} else if (!fecNac.equals(other.fecNac))
			return false;
		if (jefeId == null) {
			if (other.jefeId != null)
				return false;
		} else if (!jefeId.equals(other.jefeId))
			return false;
		if (nDIEmp == null) {
			if (other.nDIEmp != null)
				return false;
		} else if (!nDIEmp.equals(other.nDIEmp))
			return false;
		if (nomEmp == null) {
			if (other.nomEmp != null)
				return false;
		} else if (!nomEmp.equals(other.nomEmp))
			return false;
		if (Double.doubleToLongBits(salEmp) != Double.doubleToLongBits(other.salEmp))
			return false;
		if (sexEmp != other.sexEmp)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Empleado [nDIEmp=" + nDIEmp + ", nomEmp=" + nomEmp + ", sexEmp=" + sexEmp + ", fecNac=" + fecNac
				+ ", fecIncorporacion=" + fecIncorporacion + ", salEmp=" + salEmp + ", cargoE=" + cargoE + ", jefeId="
				+ jefeId + ", codDepto=" + codDepto + ", comisionE=" + comisionE + "]";
	}
	
	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
