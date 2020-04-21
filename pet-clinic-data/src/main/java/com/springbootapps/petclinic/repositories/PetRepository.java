package com.springbootapps.petclinic.repositories;

import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Long> {

    Optional<Pet> findByPetType(PetType petType);
}
