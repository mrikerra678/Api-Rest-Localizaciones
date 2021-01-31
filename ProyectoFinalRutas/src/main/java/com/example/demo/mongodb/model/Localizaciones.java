package com.example.demo.mongodb.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Localizaciones")


public class Localizaciones {
	
	private String nombre;
	private double area;
	private List<Rutas> listaRutas;
	

	public Localizaciones() {
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	
	public List<Rutas> getListaRutas(){
		return listaRutas;
	}
	
	
	public void setListaRutas(List<Rutas> listaRutas) {
		this.listaRutas = listaRutas;
	
	}
	
	
	
	


}
