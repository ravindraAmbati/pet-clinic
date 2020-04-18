package com.springbootapps.petclinic.services;

import com.springbootapps.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
