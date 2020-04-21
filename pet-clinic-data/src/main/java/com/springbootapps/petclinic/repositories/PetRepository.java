package com.springbootapps.petclinic.repositories;

import com.springbootapps.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
