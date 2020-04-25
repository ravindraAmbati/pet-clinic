package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapRepository<T extends BaseEntity, ID extends Long> {

    protected HashMap<Long, T> map = new HashMap<>();

    T findById(ID id) {
        return map.get(id);
    }

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T save(T obj) {
        if (null != obj) {
            if (null == obj.getId()) {
                obj.setId(getNextId());
            }
            return map.put(obj.getId(), obj);
        } else {
            throw new RuntimeException("Object cannot be null");
        }
    }

    void delete(T obj) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(obj));
    }

    void deleteById(ID id) {
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(findById(id)));
    }

    public Long getNextId() {
        Long nextId = 0L;
        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException nsee) {
            nextId++;
            System.out.println(nsee.getStackTrace());
        }
        return nextId;
    }

    public long count() {
        return this.map.size();
    }

}
