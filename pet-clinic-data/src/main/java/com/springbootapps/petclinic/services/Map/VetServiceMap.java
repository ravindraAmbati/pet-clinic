package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.services.SpecialityService;
import com.springbootapps.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile("mapService")
@Service
public class VetServiceMap extends AbstractMapRepository<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

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
            if (null != obj.getSpecialities() && obj.getSpecialities().size() > 0) {
                obj.getSpecialities().forEach(speciality -> {
                    if (null != speciality) {
                        specialityService.save(speciality);
                    }
                });
            } else {
                throw new RuntimeException("At least of one Specialist is required");
            }
            return super.save(obj);
        } else {
            throw new RuntimeException("Vet must not be null");
        }

    }

    public int getSize() {
        return super.getSize();
    }
}
