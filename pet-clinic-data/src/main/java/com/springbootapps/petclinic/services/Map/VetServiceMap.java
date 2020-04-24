package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile({"default", "mapService"})
@Service
public class VetServiceMap extends AbstractMapRepository<Vet, Long> implements VetService {

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet obj) {
        super.delete(obj);
    }

    @Override
    public Vet save(Vet obj) {
        return super.save(obj);
    }

    public long count() {
        return super.count();
    }
}
