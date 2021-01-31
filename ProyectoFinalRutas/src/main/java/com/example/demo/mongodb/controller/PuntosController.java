package com.example.demo.mongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mongodb.model.Localizaciones;
import com.example.demo.mongodb.model.Puntos;
import com.example.demo.mongodb.model.Rutas;
import com.example.demo.mongodb.repository.LocalizacionesRepository;



@RestController
@RequestMapping("/puntos")
@CrossOrigin(origins = "*")


public class PuntosController {


	@Autowired
	
	private LocalizacionesRepository repository2;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	
	 @GetMapping("/buscarPunto/all")
	  public List<Puntos> getListaPuntos() {
		
		 		//Lista de las localizaciones
			 	List<Localizaciones> listaLocalizaciones = repository2.findAll();
		 		//Lista de las localizaciones
			 	List<Rutas> listaRutas = new ArrayList<Rutas>();
			    //Lista de Puntos
			 	List<Puntos> listaPuntos = new ArrayList<Puntos>();
			    
			    //Recorremos la lista de Localizacione y los guardamos en la lista de Rutas
			    for(int i = 0; i<listaLocalizaciones.size();i++) {
				    for(int y = 0; y<listaLocalizaciones.get(i).getListaRutas().size();y++) {
					   listaRutas.add(listaLocalizaciones.get(i).getListaRutas().get(y));
					   
				    }
				}
			    //recorremos la lista de Rutas y guardamos en la lista de Puntos
			    for(int i = 0;i<listaRutas.size();i++) {
			    	for(int y = 0;y<listaRutas.get(i).getListaPuntos().size();y++) {
					   listaPuntos.add(listaRutas.get(i).getListaPuntos().get(y));
				   }
			  }
			    //devolvemos todos los puntos de todas las rutas y de las localizaciones
			    return listaPuntos;

		 }
	 
	 @GetMapping("/buscarPunto/{idPunto}")
	  public List<Puntos> getListaPuntos(@PathVariable String idPunto) {
		
	 			//Lista de las localizaciones
			 	List<Localizaciones> listaLocalizaciones = repository2.findAll();
		 		
			 	//Lista de las localizaciones
			 	List<Rutas> listaRutas = new ArrayList<Rutas>();

			 	//Lista de Puntos
			 	List<Puntos> listaPuntos = new ArrayList<Puntos>();
			    
			    //Lista de un punto
			    List<Puntos> listaUnPunto = new ArrayList<Puntos>();
			    
			    
			    //Recorremos la lista de Localizacione y los guardamos en la lista de Rutas
			    for(int i = 0; i<listaLocalizaciones.size();i++) {
				    for(int y = 0; y<listaLocalizaciones.get(i).getListaRutas().size();y++) {
					   listaRutas.add(listaLocalizaciones.get(i).getListaRutas().get(y));
					   
				    }
				}
			    
			    
			    //recorremos la lista de Rutas y guardamos en la lista de Puntos
			    for(int i = 0;i<listaRutas.size();i++) {
			    	for(int y = 0;y<listaRutas.get(i).getListaPuntos().size();y++) {
					   listaPuntos.add(listaRutas.get(i).getListaPuntos().get(y));
				   }
			  }
			    
			    
			    //recorremos la lista de Puntos y guardamos en la lista de un punto
			    for(Puntos punto : listaPuntos) {
			    	if(punto.getNombre().equals(idPunto)) {
			    		listaUnPunto.add(punto);
			    	}
			    }
			  
			    //Devolvemos un punto
			    return listaUnPunto;

		 }
	 
	 @PostMapping("/nuevoPunto/{idLocalizacion}/{idRuta}")
	  public void insertarPunto(@PathVariable String idLocalizacion,@PathVariable String idRuta, @RequestBody Puntos puntoNuevo) {
	 
		//Guarda una localizacion filtrado por el nombre de la localizacion de la query.
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 
		 //Recorremos las rutas dentro de la lista de rutas en la localizacion  
		 for (Rutas ruta :  localizacion.getListaRutas()) {
			 
			//filtro para actualizar segun el nombre de la ruta de la query 
			if(ruta.getNombre().equals(idRuta)) {
				ruta.getListaPuntos().add(puntoNuevo);
			}
		}
		 
		
		 // Inserccion en la localizacion seleccionada y en la ruta seleccionado y añadimos en la listaPuntos el nuevo punto del Body de la llamada
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 Update update = Update.update("listaRutas", localizacion.getListaRutas());		 
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);

		
	    
	 }
	 
	 @PutMapping("/modPunto/{idLocalizacion}/{idRuta}/{idPunto}")
	  public void actualizarPunto(@PathVariable String idLocalizacion,@PathVariable String idRuta,@PathVariable String idPunto, @RequestBody Puntos nuevoPunto	) {
	 
		//Guarda una localizacion filtrado por el nombre de la localizacion de la query.
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		
		 //Recorremos las rutas dentro de la lista de rutas en la localizacion  
		 for (Rutas rut : localizacion.getListaRutas()) {
			 
			 //filtro para actualizar segun el nombre de la ruta de la query 
			 if(rut.getNombre().equals(idRuta)) {
				 //Recorremos los puntos en la lista de Puntos
				 for(Puntos punto: rut.getListaPuntos()) {
					 //filtro para actualizar segun el nombre de la ruta de la query 
					 if(punto.getNombre().equals(idPunto)) {
						 punto.setNombre(nuevoPunto.getNombre());
						 punto.setArea_total(nuevoPunto.getArea_total());
						 punto.setLat(nuevoPunto.getLat());
						 punto.setLog(nuevoPunto.getLog());
						 punto.setOculto(nuevoPunto.isOculto());
						 punto.setRuta(nuevoPunto.getRuta());
						 punto.setTipo(nuevoPunto.getTipo());
						 
					 }
				 }
				
			 }
		}
		 //actualizacion segun el nombre
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 new Update();
		 Update update = Update.update("listaRutas", localizacion.getListaRutas());
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);
		
	 }
	 
	 
	 
	 @DeleteMapping("/eliminarPunto/{idLocalizacion}/{idRuta}/{idPunto}")
	  public void eliminarRuta(@PathVariable String idLocalizacion,@PathVariable String idRuta,@PathVariable String idPunto) {
		 
		
		 //Guarda una localizacion filtrado por el nombre de la localizacion de la query.
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 
		 //Guardamos los datos de la ruta que vamos a eliminar el punto
		 Rutas ru = null;
		 //Guardamos el punto para eliminar
		 Puntos pun = null;
		 
		 //recorremos las rutas de la localizacion
		 for (Rutas ruta :  localizacion.getListaRutas()) {

			 //filtro para eliminar segun el nombre de la ruta de la query 
			if(ruta.getNombre().equals(idRuta)) {
				//guardamos la ruta
				ru = ruta;
				//recorremos los puntos de la ruta guardadd
				for(Puntos punto : ruta.getListaPuntos()) {
					
					 //filtro para eliminar segun el nombre del punto de la query 
					if(punto.getNombre().equals(idPunto)) {
						pun =punto;
						
					}
				}
			}
		}
		 
		 //eliminamos el punto de la ruta
		 ru.getListaPuntos().remove(pun);
		 
		 //eliminamos segun el nombre de la localizacion
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 new Update();
		 Update update = Update.update("listaRutas",ru);
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);
	
	 
	 
	 }
}


	 
