package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner obj) {
        super.delete(obj);
    }

    @Override
    public Owner save(Owner obj) {
        if (null != obj) {
            if (null == obj.getId()) {
                obj.setId(super.getNextId());
            }
            if (!(null != obj.getPets() && obj.getPets().size() > 0)) {
                throw new RuntimeException("At least of one Pet is mandatory");
            }
        }
        return super.save(obj);
    }

    @Override
    public Owner findByLastName(String lastName) {
        throw new UnsupportedOperationException("findByLastName() is not supported");
    }
}
