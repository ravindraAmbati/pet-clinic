package com.springbootapps.petclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {

    T findById(ID id);

    Set<T> findAll();

    T save(T t);

    boolean delete(T t);

    boolean deleteById(ID id);
}
