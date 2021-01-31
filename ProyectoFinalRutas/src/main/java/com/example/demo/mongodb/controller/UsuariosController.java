package com.example.demo.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

import com.example.demo.mongodb.model.Usuarios;
import com.example.demo.mongodb.repository.UsuariosRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")

public class UsuariosController {

	@Autowired
	private UsuariosRepository repository;

	@Autowired
	 private MongoTemplate mongoTemplate;

	// LLAMDAS GET de Usuarios

	@CrossOrigin(origins = "*")
	@GetMapping("/all")
	public List<Usuarios> getUsuarios() {
		
		
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "puntuacion_total"));
		List<Usuarios> users = mongoTemplate.find(query,Usuarios.class);
		
		return users;
	}
	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public Usuarios loginUsuarios(@RequestBody Usuarios usuario) {
		System.out.print("Usuario que intenta loguearse:" + usuario.getUsuario() + "\n\r");

		List<Usuarios> aUsuarios = repository.findAll();

		for (Usuarios u : aUsuarios) {
			// System.out.print(" Usuario: " + u.getUsuario());
			// System.out.print(" Contraseña: " + u.getContraseña());

			if (usuario.getUsuario().equals(u.getUsuario()) && usuario.getContraseña().equals(u.getContraseña())) {
				System.out.print("Usuario que se ha logueado: " + usuario.getUsuario()+ "\n\r");
				return u;
			}
		}

		return null;

	}

	// LLAMADAS POST DE LOS USUARIOS
	@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public void registerUsername(@RequestBody Usuarios usu) {
		repository.save(usu);

	}
	@CrossOrigin(origins = "*")
	@PutMapping("/cambioContrasena")
	public Usuarios restartPassword(@RequestBody Usuarios usu) {

		return repository.save(usu);
	}
	@CrossOrigin(origins = "*")
	@DeleteMapping("/eliminarUsuario")
	public void deleteUsername(@RequestBody Usuarios usu) {

		repository.delete(usu);

	}
	@CrossOrigin(origins = "*")
	@PutMapping("/modificarUsuario/{usuario}")
	public Usuarios restartPassword(@PathVariable String usuario,@RequestBody Usuarios usu) {
		Query query = new Query(Criteria.where("usuario").is(usuario));		
		Update update = new Update();
		update.set("usuario", usu.getUsuario());
		update.set("apellido", usu.getApellido());
		update.set("contrasena", usu.getContraseña());
		update.set("email",usu.getEmail());
		update.set("ruta_activa", usu.getRuta_activa());
		update.set("lat", usu.getLat());
		update.set("log",usu.getLog());
		update.set("puntuacion_total", usu.getPuntuacion_total());
		
		mongoTemplate.findAndModify(query, update, Usuarios.class);
		
		return usu;
  
	}

}
