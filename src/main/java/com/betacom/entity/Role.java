package com.betacom.entity;

public class Role {
	
	private Integer id;
	private Double stipendioMin;
	private String descrizione;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getStipendioMin() {
		return stipendioMin;
	}
	
	public void setStipendioMin(Double stipendioMin) {
		this.stipendioMin = stipendioMin;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	//toString
	@Override
	public String toString() {
		return "Role [id=" + id + ", stipendioMin=" + stipendioMin + ", descrizione=" + descrizione + "]";
	}
	
}
