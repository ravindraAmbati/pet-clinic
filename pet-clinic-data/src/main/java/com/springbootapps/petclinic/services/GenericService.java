package com.springbootapps.petclinic.services;

import java.util.Set;

public interface GenericService<T> {

    T findById(Long id);
    Set<T> findAll();
    T save(T t);
}
