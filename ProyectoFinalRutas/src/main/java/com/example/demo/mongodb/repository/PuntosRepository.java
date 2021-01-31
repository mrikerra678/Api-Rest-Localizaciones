package com.example.demo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongodb.model.Puntos;

public interface PuntosRepository extends MongoRepository<Puntos,String>  {

}
