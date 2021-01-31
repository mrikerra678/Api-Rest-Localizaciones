package com.example.demo.mongodb.model;

//import org.bson.types.Binary;
//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="Usuarios")

public class Usuarios {
	
	
	private int id_usuario;
	
	private String usuario;                                    
	                                                           
	private String apellido;                                   
	                                                           
	private String contraseña;                                 
	                                                           
	private String email;                                      
	                                                           
	private String ruta_activa;                                
	                                                           
	private double lat;                                        
	
	private double log;

	private int puntuacion_total;
	
	
	
	

	

	public int getPuntuacion_total() {
		return puntuacion_total;
	}


	public void setPuntuacion_total(int puntuacion_total) {
		this.puntuacion_total = puntuacion_total;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contrasena) {
		this.contraseña = contrasena;
	}


	public String getRuta_activa() {
		return ruta_activa;
	}


	public void setRuta_activa(String ruta_activa) {
		this.ruta_activa = ruta_activa;
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


	public int getId_usuario() {
		return id_usuario;
	}
	
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Usuarios() {
		
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	//private Binary avatar; -- EN FUTURAS ACTUALIZACIONES
	
	

	/*public Binary getAvatar() {
		return avatar;
	}

	public void setAvatar(Binary avatar) {
		this.avatar = avatar;
	}
*/
	
	
	
}
