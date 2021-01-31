package com.example.demo.mongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
import com.example.demo.mongodb.model.Rutas;
import com.example.demo.mongodb.repository.LocalizacionesRepository;


@RestController
@RequestMapping("/rutas")
@CrossOrigin(origins = "*")


public class RutasController {

	
	@Autowired
	private LocalizacionesRepository repository2;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	
	 @GetMapping("/buscarRutas/all")
	  public List<Rutas> getTodasRutas() {
	 
	    List<Localizaciones> listaLocalizaciones = repository2.findAll();
	    List<Rutas> listaRutas = new ArrayList<Rutas>();
	    
	   
	    System.out.print(listaLocalizaciones.size());
	    
	    
	    for(int i = 0; i<listaLocalizaciones.size();i++) {
		    for(int y = 0; y<listaLocalizaciones.get(i).getListaRutas().size();y++) {
			   listaRutas.add(listaLocalizaciones.get(i).getListaRutas().get(y));
		    }
		}
	   
	    return listaRutas;
	  }
	 
	 @GetMapping("/buscarRutas/{idRuta}")
	  public List<Rutas> getUnaRutas(@PathVariable String idRuta) {
	 
	    List<Localizaciones> listaLocalizaciones = repository2.findAll();
	    List<Rutas> listaRutas = new ArrayList<Rutas>();
	    
	    List<Rutas> listaUnaRuta = new ArrayList<Rutas>();
	    	    
	    for(int i = 0; i<listaLocalizaciones.size();i++) {
		    for(int y = 0; y<listaLocalizaciones.get(i).getListaRutas().size();y++) {
			   listaRutas.add(listaLocalizaciones.get(i).getListaRutas().get(y));
		    }
		}
	    
	    for(Rutas ruta : listaRutas) {

	    	if(ruta.getNombre().equals(idRuta)) {
	    		listaUnaRuta.add(ruta);
	    	}
	    }
	   
	    return listaUnaRuta;
	 }


	 @GetMapping("/buscarRutasporloc/{idLocalizaciones}")
	 public List<Rutas> getRutas(@PathVariable String idLocalizaciones) {
	
	   Localizaciones loc = repository2.findByNombre(idLocalizaciones);
	   
	   List<Rutas> listaRutas = new ArrayList<Rutas>();
	   
	  for(Rutas ru: loc.getListaRutas()) {
		  listaRutas.add(ru);
		  
	  }
	  
	  return listaRutas;
	}
	 
	 @PostMapping("/nuevaRuta/{idLocalizacion}")
	  public void insertarRuta(@PathVariable String idLocalizacion, @RequestBody Rutas rutaNueva) {
	 
		 List<Localizaciones> listaLocalizaciones = repository2.findAll();
		 for(Localizaciones loc : listaLocalizaciones) {
			 if(loc.getNombre().equals(idLocalizacion)) {
				Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
				Update update = new Update().push("listaRutas",rutaNueva);				
				mongoTemplate.findAndModify(query, update, Localizaciones.class);
			 }
		 }
		
	    
	 }
	 
	 @PutMapping("/modRuta/{idLocalizacion}/{idRuta}")
	  public void actualizarRuta(@PathVariable String idLocalizacion,@PathVariable String idRuta, @RequestBody Rutas nuevaRuta 	) {
	 
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 
		 //localizacion.getListaRutas();
		 for (Rutas rut : localizacion.getListaRutas()) {
			 if(rut.getNombre().equals(idRuta)) {
				 rut.setNombre(nuevaRuta.getNombre());
				 rut.setKm_totales(nuevaRuta.getKm_totales());
				 rut.setTiempo(nuevaRuta.getTiempo());
				 rut.setTransporte(nuevaRuta.getTransporte());
			 }
		}
		 
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 new Update();
		Update update = Update.update("listaRutas", localizacion.getListaRutas());
		 //System.out.print(localizacion.getListaRutas().get(0).getNombre());
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);
		
		 
	 }
		
	
	 
	 @DeleteMapping("/eliminarRuta/{idLocalizacion}/{idRuta}")
	  public void eliminarRuta(@PathVariable String idLocalizacion,@PathVariable String idRuta) {
		 
		 
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 
		 Rutas ru = null;
		 for (Rutas ruta :  localizacion.getListaRutas()) {
			if(ruta.getNombre().equals(idRuta)) {
				ru = ruta;
			}
		}
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 
		 Update update = new Update().pull("listaRutas",ru);
		 //System.out.print(localizacion.getListaRutas().get(0).getNombre());
		 
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);
	
						
						
		}
}
			
				 


			 
		 
		
	    
	 
	 
	 

