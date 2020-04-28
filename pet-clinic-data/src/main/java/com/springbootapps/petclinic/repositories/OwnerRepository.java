package com.springbootapps.petclinic.repositories;

import com.springbootapps.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Optional<Owner> findByLastName(String name);

    List<Owner> findAllByLastNameLike(String lastName);
}
