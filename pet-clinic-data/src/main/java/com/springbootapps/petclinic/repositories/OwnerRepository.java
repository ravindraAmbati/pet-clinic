package com.springbootapps.petclinic.repositories;

import com.springbootapps.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
