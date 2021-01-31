package com.example.demo.mongodb.controller;

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
import com.example.demo.mongodb.repository.LocalizacionesRepository;

@RestController
@RequestMapping("/localizaciones")
@CrossOrigin(origins = "*")

public class LocalizacionesController {

	@Autowired
	private LocalizacionesRepository repository;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@GetMapping("/all")
	  public List<Localizaciones> getLocalizaciones() {
		   return repository.findAll();
	  }
	  @GetMapping("/buscarLocalizaciones/{idLocalizacion}")
	  public Localizaciones getunaLocalizaciones(@PathVariable String idLocalizacion) {
		 //Localizaciones loc;
		 
		 return repository.findByNombre(idLocalizacion);
			
		
		
	
	}
	
	@PostMapping("/nuevaLocalizacion")
	public void setLocalizaciones(@RequestBody Localizaciones loc) {
		repository.save(loc);
	}
	
	@PutMapping("/editarLocalizacion/{idPunto}")
	public void updateLocalizaciones(@PathVariable String idPunto, @RequestBody Localizaciones loc) {
		
		Query query = new Query(Criteria.where("nombre").is(idPunto));		
		Update update = new Update();
		update.set("nombre", loc.getNombre());
		update.set("area", loc.getArea());
		update.set("listaRutas",loc.getListaRutas());
		//update.set("listaPuntos", loc.getListaPuntos());
		//update.set("listaPreguntas", loc.getListaPreguntas());
		mongoTemplate.findAndModify(query, update, Localizaciones.class);
	}
	
	@DeleteMapping("/eliminarLocalizacion/{id}")
	public void deleteLocalizaciones(@PathVariable String id) {
		
		repository.deleteByNombre(id);
	}

	
}
