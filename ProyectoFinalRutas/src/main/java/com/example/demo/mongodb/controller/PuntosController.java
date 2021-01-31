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
		
			 List<Localizaciones> listaLocalizaciones = repository2.findAll();
			    List<Rutas> listaRutas = new ArrayList<Rutas>();
			    List<Puntos> listaPuntos = new ArrayList<Puntos>();
			    
			    
			    for(int i = 0; i<listaLocalizaciones.size();i++) {
				    for(int y = 0; y<listaLocalizaciones.get(i).getListaRutas().size();y++) {
					   listaRutas.add(listaLocalizaciones.get(i).getListaRutas().get(y));
					   
				    }
				}
			    
			    for(int i = 0;i<listaRutas.size();i++) {
			    	for(int y = 0;y<listaRutas.get(i).getListaPuntos().size();y++) {
					   listaPuntos.add(listaRutas.get(i).getListaPuntos().get(y));
				   }
			  }
			    return listaPuntos;

		 }
	 
	 @GetMapping("/buscarPunto/{idPunto}")
	  public List<Puntos> getListaPuntos(@PathVariable String idPunto) {
		
			 	List<Localizaciones> listaLocalizaciones = repository2.findAll();
			    List<Rutas> listaRutas = new ArrayList<Rutas>();
			    List<Puntos> listaPuntos = new ArrayList<Puntos>();
			    
			    List<Puntos> listaUnPunto = new ArrayList<Puntos>();
			    
			    
			    for(int i = 0; i<listaLocalizaciones.size();i++) {
				    for(int y = 0; y<listaLocalizaciones.get(i).getListaRutas().size();y++) {
					   listaRutas.add(listaLocalizaciones.get(i).getListaRutas().get(y));
					   
				    }
				}
			    
			    for(int i = 0;i<listaRutas.size();i++) {
			    	for(int y = 0;y<listaRutas.get(i).getListaPuntos().size();y++) {
					   listaPuntos.add(listaRutas.get(i).getListaPuntos().get(y));
				   }
			  }
			    
			    for(Puntos punto : listaPuntos) {
			    	if(punto.getNombre().equals(idPunto)) {
			    		listaUnPunto.add(punto);
			    	}
			    }
			  
			    
			    return listaUnPunto;

		 }
	 
	 @PostMapping("/nuevoPunto/{idLocalizacion}/{idRuta}")
	  public void insertarPunto(@PathVariable String idLocalizacion,@PathVariable String idRuta, @RequestBody Puntos puntoNuevo) {
	 
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 for (Rutas ruta :  localizacion.getListaRutas()) {
			if(ruta.getNombre().equals(idRuta)) {
				ruta.getListaPuntos().add(puntoNuevo);
			}
		}
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 Update update = Update.update("listaRutas", localizacion.getListaRutas());
		// System.out.print(localizacion.getListaRutas().get(0).getNombre());
		 
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);

		
	    
	 }
	 
	 @PutMapping("/modPunto/{idLocalizacion}/{idRuta}/{idPunto}")
	  public void actualizarPunto(@PathVariable String idLocalizacion,@PathVariable String idRuta,@PathVariable String idPunto, @RequestBody Puntos nuevoPunto	) {
	 
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 for (Rutas rut : localizacion.getListaRutas()) {
			 if(rut.getNombre().equals(idRuta)) {
				 for(Puntos punto: rut.getListaPuntos()) {
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
		 
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 new Update();
		Update update = Update.update("listaRutas", localizacion.getListaRutas());
		 System.out.print(localizacion.getListaRutas().get(0).getNombre());
		 
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);
		
	 }
	 
	 @DeleteMapping("/eliminarPunto/{idLocalizacion}/{idRuta}/{idPunto}")
	  public void eliminarRuta(@PathVariable String idLocalizacion,@PathVariable String idRuta,@PathVariable String idPunto) {
		 
		 
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 
		 Rutas ru = null;
		 Puntos pun = null;
		 for (Rutas ruta :  localizacion.getListaRutas()) {
			if(ruta.getNombre().equals(idRuta)) {
				ru = ruta;
				for(Puntos punto : ruta.getListaPuntos()) {
					if(punto.getNombre().equals(idPunto)) {
						pun =punto;
						
					}
				}
			}
		}
		 
		 ru.getListaPuntos().remove(pun);
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 
		 new Update();
		 Update update = Update.update("listaRutas",ru);
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);
	
	 
	 
	 }
}


	 
