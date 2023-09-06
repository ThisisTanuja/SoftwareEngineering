package com.sports.goteam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sports.goteam.model.Player;

//This annotation identifies the class as a Spring Data JPA repository.
//Spring Data JPA automatically generates the implementation for the CRUD methods based on the entity we create.

@Repository

//This interface extends CrudRepository that provides basic CRUD operations for the Player entity.
//This repository is parameterized with Player representing the entity type and Long representing the type of type
//of the entity's primary key.
public interface PlayerRepository extends CrudRepository<Player, Long>{

}
