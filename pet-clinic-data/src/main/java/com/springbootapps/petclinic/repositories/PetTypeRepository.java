package com.springbootapps.petclinic.repositories;

import com.springbootapps.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
