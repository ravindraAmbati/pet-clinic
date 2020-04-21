package com.springbootapps.petclinic.repositories;

import com.springbootapps.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
