package com.springbootapps.petclinic.services;

import com.springbootapps.petclinic.model.Owner;

public interface OwnerService extends GenericService<Owner> {
    Owner findByLastName(String lastName);
}
