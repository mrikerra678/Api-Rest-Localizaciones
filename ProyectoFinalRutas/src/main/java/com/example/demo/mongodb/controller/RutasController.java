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
	 
		 //Lista de todas las localizaciones
	    List<Localizaciones> listaLocalizaciones = repository2.findAll();
	    //Lista de Rutas que guardaremos todas las rutas de todas las localizaciones
	    List<Rutas> listaRutas = new ArrayList<Rutas>();
	    
	    //lista de la ruta que el usuario busca mediante el nombre de la query
	    List<Rutas> listaUnaRuta = new ArrayList<Rutas>();
	    	    
	    //recorremos la lista de localizaciones
	    for(int i = 0; i<listaLocalizaciones.size();i++) {
		    for(int y = 0; y<listaLocalizaciones.get(i).getListaRutas().size();y++) {
		    	//Recorremos la lista de las rutas dentro de las localizaciones y guardamos en otra lista
			   listaRutas.add(listaLocalizaciones.get(i).getListaRutas().get(y));
		    }
		}
	    
	    //recorremos la lista de todas las rutas y filtramos la ruta que queremos buscar
	    for(Rutas ruta : listaRutas) {
	    	//filtro para encontrar 
	    	if(ruta.getNombre().equals(idRuta)) {
	    		listaUnaRuta.add(ruta);
	    	}
	    }
	   //devolvemos la ruta que el usuario ha buscado de la query
	    return listaUnaRuta;
	 }
	 
	 @GetMapping("/buscarRutasporloc/{idLocalizaciones}")
	  public List<Rutas> getRutas(@PathVariable String idLocalizaciones) {
	 
		//Guarda una localizacion filtrado por el nombre de la query.
	    Localizaciones loc = repository2.findByNombre(idLocalizaciones);
	    
	    //Lista de rutas
	    List<Rutas> listaRutas = new ArrayList<Rutas>();
	    
	    //recorremos la localizacion para guardar las rutas en una lista nueva
	   for(Rutas ru: loc.getListaRutas()) {
		   listaRutas.add(ru);
		   
	   }
	   //devolvemos la lista con todas las rutas de una localizaicon
	   return listaRutas;
	 }
	 
	 
	 
	 @PostMapping("/nuevaRuta/{idLocalizacion}")
	  public void insertarRuta(@PathVariable String idLocalizacion, @RequestBody Rutas rutaNueva) {
	 
		 //Lista de todas las localizaciones 
		 List<Localizaciones> listaLocalizaciones = repository2.findAll();
		 //Recorremos las localizaciones de la lista de las localizaciones
		 for(Localizaciones loc : listaLocalizaciones) {
			 //filtro para crear una nueva ruta en la localizacion seleccionada en la query
			 if(loc.getNombre().equals(idLocalizacion)) {
				// Inserccion en la localizacion seleccionada y añadimos en la listaRutas la nueva ruta del Body de la llamada
				Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
				Update update = new Update().push("listaRutas",rutaNueva);				
				mongoTemplate.findAndModify(query, update, Localizaciones.class);
			 }
		 }
		
	    
	 }
	 
	 @PutMapping("/modRuta/{idLocalizacion}/{idRuta}")
	  public void actualizarRuta(@PathVariable String idLocalizacion,@PathVariable String idRuta, @RequestBody Rutas nuevaRuta 	) {
	 
		//Guarda una localizacion filtrado por el nombre de la query.
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 
		 //Recorre las rutas dentro de la lista rutas de localizaciones
		 for (Rutas rut : localizacion.getListaRutas()) {
			 //filtro para actualizar segun el nombre de la ruta de la query
			 if(rut.getNombre().equals(idRuta)) {
				 //sobreescribimos todos los datos cogiendo de la ruta body
				 rut.setNombre(nuevaRuta.getNombre());
				 rut.setKm_totales(nuevaRuta.getKm_totales());
				 rut.setTiempo(nuevaRuta.getTiempo());
				 rut.setTransporte(nuevaRuta.getTransporte());
			 }
		}
		 
		 //actualizacion segun el nombre
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 new Update();
		Update update = Update.update("listaRutas", localizacion.getListaRutas());
		 //System.out.print(localizacion.getListaRutas().get(0).getNombre());
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);
		
		 
	 }
		
	
	 
	 @DeleteMapping("/eliminarRuta/{idLocalizacion}/{idRuta}")
	  public void eliminarRuta(@PathVariable String idLocalizacion,@PathVariable String idRuta) {
		 
		 //Guarda una localizacion filtrado por el nombre de la query.
		 Localizaciones localizacion = repository2.findByNombre(idLocalizacion);
		 //Ruta que elimina
		 Rutas ru = null;
		 //Recorre las rutas dentro de la lista de rutas de la localizacion
		 for (Rutas ruta :  localizacion.getListaRutas()) {
			 //filtro para eliminar segun el nombre de la ruta de la query
			if(ruta.getNombre().equals(idRuta)) {
				ru = ruta;
			}
		}
		 
		 //eliminar de la ruta
		 Query query = new Query(Criteria.where("nombre").is(idLocalizacion));
		 Update update = new Update().pull("listaRutas",ru); 
		 mongoTemplate.findAndModify(query, update, Localizaciones.class);
	
						
						
		}
}
			
				 


			 
		 
		
	    
	 
	 
	 

