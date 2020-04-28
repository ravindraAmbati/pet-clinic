package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Profile({"default", "mapService"})
@Service
public class OwnerServiceMap extends AbstractMapRepository<Owner, Long> implements OwnerService {

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
        return super.save(obj);
    }

    @Override
    public Owner findByLastName(String lastName) {
        throw new UnsupportedOperationException("findByLastName() is not supported");
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        //todo implement findAllByLastNameLike()
        return null;
    }

    public long count() {
        return super.count();
    }
}
