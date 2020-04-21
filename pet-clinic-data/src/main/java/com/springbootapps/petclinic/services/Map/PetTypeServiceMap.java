package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeServiceMap extends AbstractMapRepository<PetType, Long> implements PetTypeService {
    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PetType obj) {
        super.delete(obj);
    }

    @Override
    public PetType save(PetType obj) {
        return super.save(obj);
    }

    @Override
    public PetType findByName(String name) {
        throw new UnsupportedOperationException("this service is not implemented yet");
    }

    public int getSize() {
        return super.getSize();
    }
}
