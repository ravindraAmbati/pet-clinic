package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Visit;
import com.springbootapps.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile("mapService")
@Service
public class VisitServiceMap extends AbstractMapRepository<Visit, Long> implements VisitService {

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit obj) {
        super.delete(obj);
    }

    @Override
    public Visit save(Visit obj) {
        if (null != obj) {
            if (null != obj.getPet() && null != obj.getPet().getId()) {
                if (null != obj.getPet().getOwner() && null != obj.getPet().getOwner().getId()) {
                    return super.save(obj);
                } else {
                    throw new RuntimeException("Invalid Visit - Owner is required for the pet" + obj.getPet().getName());
                }
            } else {
                throw new RuntimeException("Invalid Visit - Pet is required");
            }
        }
        {
            throw new RuntimeException("Visit must not be null");
        }

    }
}
