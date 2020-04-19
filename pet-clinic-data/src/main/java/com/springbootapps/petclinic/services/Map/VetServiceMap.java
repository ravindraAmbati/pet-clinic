package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

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
        if (null != obj) {
            if (null == obj.getId()) {
                obj.setId(super.getNextId());
            }
            if (!(null != obj.getSpecialities() && obj.getSpecialities().size() > 0)) {
                throw new RuntimeException("At least of one Specialist is required");
            }
        }
        return super.save(obj);
    }
}
