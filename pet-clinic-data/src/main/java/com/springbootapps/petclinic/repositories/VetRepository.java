package com.springbootapps.petclinic.repositories;

import com.springbootapps.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
