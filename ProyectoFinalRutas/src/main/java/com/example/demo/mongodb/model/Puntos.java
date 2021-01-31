package com.example.demo.mongodb.model;

import java.util.List;


public class Puntos {
	
	private String nombre;
	private double lat;
	private double log;
	private int area_total;
	private boolean oculto;
	private String tipo;
	private String ruta;
	private List<Preguntas> listaPreguntas;
	
	
	
	public Puntos() {
		
	}


	public String getRuta() {
		return ruta;
	}



	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public double getLat() {
		return lat;
	}



	public void setLat(double lat) {
		this.lat = lat;
	}



	public double getLog() {
		return log;
	}



	public void setLog(double log) {
		this.log = log;
	}



	public int getArea_total() {
		return area_total;
	}



	public void setArea_total(int area_total) {
		this.area_total = area_total;
	}



	public boolean isOculto() {
		return oculto;
	}



	public void setOculto(boolean oculto) {
		this.oculto = oculto;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public List<Preguntas> getListaPreguntas() {
		return listaPreguntas;
	}



	public void setListaPreguntas(List<Preguntas> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}



}
