package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Speciality;
import com.springbootapps.petclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile({"default", "mapService"})
@Service
public class SpecialityServiceMap extends AbstractMapRepository<Speciality, Long> implements SpecialityService {

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality obj) {
        super.delete(obj);
    }

    @Override
    public Speciality save(Speciality obj) {
        return super.save(obj);
    }

    public long count() {
        return super.count();
    }
}
