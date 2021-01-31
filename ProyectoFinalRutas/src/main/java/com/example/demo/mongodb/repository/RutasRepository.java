package com.example.demo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongodb.model.Rutas;

public interface RutasRepository extends MongoRepository<Rutas,String>  {

}
