package com.springbootapps.petclinic.services;

import com.springbootapps.petclinic.model.PetType;

public interface PetTypeService extends CrudService<PetType, Long> {

    PetType findByName(String name);
}
