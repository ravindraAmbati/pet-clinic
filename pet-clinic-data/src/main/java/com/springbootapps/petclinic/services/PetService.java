package com.springbootapps.petclinic.services;

import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;

public interface PetService extends GenericService<Pet> {

    Pet findByPetType(PetType petType);
}
