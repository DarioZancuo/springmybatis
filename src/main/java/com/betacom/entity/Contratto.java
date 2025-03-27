package com.betacom.entity;

import java.sql.Date;

public class Contratto {
	
	private Integer id;
	private Date dataAssunzione;
	private Role role;
	private Double stipendio;
	private Tipologia tipologia;
	private Date dataDimissione;
	private Boolean status;
	
	//Getters&Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDataAssunzione() {
		return dataAssunzione;
	}
	
	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Double getStipendio() {
		return stipendio;
	}
	public void setStipendio(Double stipendio) {
		this.stipendio = stipendio;
	}
	
	public Tipologia getTipologia() {
		return tipologia;
	}
	
	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}
	
	public Date getDataDimissione() {
		return dataDimissione;
	}
	
	public void setDataDimissione(Date dataDimissione) {
		this.dataDimissione = dataDimissione;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	//toString
	@Override
	public String toString() {
		return "Contratto [id=" + id + ", dataAssunzione=" + dataAssunzione + ", role=" + role + ", stipendio="
				+ stipendio + ", tipologia=" + tipologia + ", dataDimissione=" + dataDimissione + ", status=" + status
				+ "]";
	}
	
}
