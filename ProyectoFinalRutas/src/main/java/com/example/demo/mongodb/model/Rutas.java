package com.example.demo.mongodb.model;

import java.util.List;

public class Rutas {
	
	private String nombre;
	private String transporte;
	private int tiempo;
	private int km_totales;
	private List<Puntos> listaPuntos;
	
	
	public Rutas() {
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTransporte() {
		return transporte;
	}


	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}


	public int getTiempo() {
		return tiempo;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}


	public int getKm_totales() {
		return km_totales;
	}


	public void setKm_totales(int km_totales) {
		this.km_totales = km_totales;
	}


	public List<Puntos> getListaPuntos() {
		return listaPuntos;
	}


	public void setListaPuntos(List<Puntos> listaPuntos) {
		this.listaPuntos = listaPuntos;
	}



}