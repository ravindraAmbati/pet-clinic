package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile({"default", "mapService"})
@Service
public class PetServiceMap extends AbstractMapRepository<Pet, Long> implements PetService {

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet obj) {
        super.delete(obj);
    }

    @Override
    public Pet save(Pet obj) {
        return super.save(obj);
    }

    @Override
    public Pet findByPetType(PetType petType) {
        throw new UnsupportedOperationException("findByLastName() is not supported");
    }

    public long count() {
        return super.count();
    }
}
