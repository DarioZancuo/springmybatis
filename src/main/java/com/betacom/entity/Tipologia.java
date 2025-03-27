package com.betacom.entity;

public class Tipologia {
	
	private Integer id;
	private String tipo;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	//toString
	@Override
	public String toString() {
		return "Descrizione [id=" + id + ", tipo=" + tipo + "]";
	}	

}
