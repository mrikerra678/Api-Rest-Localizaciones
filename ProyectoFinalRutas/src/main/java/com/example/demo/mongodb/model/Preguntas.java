package com.example.demo.mongodb.model;
import java.util.List;

public class Preguntas {
	
	private String pregunta;
	private List<String> listaRespuestas;
	private int respuesta_correcta;
	private int puntuacion_pregunta;
	
	
	public Preguntas() {
		
	}
	public String getPregunta() {
		return pregunta;
	}
	


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public List<String> getListaRespuestas() {
		return listaRespuestas;
	}


	public void setListaRespuestas(List<String> listaRespuestas) {
		this.listaRespuestas = listaRespuestas;
	}


	public int getRespuesta_correcta() {
		return respuesta_correcta;
	}


	public void setRespuesta_correcta(int respuesta_correcta) {
		this.respuesta_correcta = respuesta_correcta;
	}


	public int getPuntuacion_pregunta() {
		return puntuacion_pregunta;
	}


	public void setPuntuacion_pregunta(int puntuacion_pregunta) {
		this.puntuacion_pregunta = puntuacion_pregunta;
	}
	

}
