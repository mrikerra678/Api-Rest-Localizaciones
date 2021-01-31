package com.example.demo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.mongodb.model.Localizaciones;

public interface LocalizacionesRepository extends MongoRepository<Localizaciones,String>  {

	Localizaciones findByNombre(String idLocalizacion);

	void deleteByNombre(String id);


}
