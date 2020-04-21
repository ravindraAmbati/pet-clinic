package com.springbootapps.petclinic.repositories;

import com.springbootapps.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

    Optional<PetType> findByName(String name);
}
