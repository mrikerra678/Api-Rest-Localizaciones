package com.example.demo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongodb.model.Usuarios;

public interface UsuariosRepository extends MongoRepository<Usuarios,String>{

	
}
