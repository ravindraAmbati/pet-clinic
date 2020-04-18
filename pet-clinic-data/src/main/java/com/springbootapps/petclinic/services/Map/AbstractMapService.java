package com.springbootapps.petclinic.services.Map;

import java.util.HashMap;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    private HashMap<ID, T> map = new HashMap<>();

    T findById(ID id) {
        return map.get(id);
    }

    Set<T> findAll() {
        return (Set<T>) map.entrySet();
    }

    T save(ID id, T obj) {
        return map.put(id, obj);
    }

    void delete(T obj) {
        map.entrySet().remove(obj);
    }

    void deleteById(ID id) {
        map.entrySet().removeIf(idtEntry -> idtEntry.equals(findById(id)));
    }

}
