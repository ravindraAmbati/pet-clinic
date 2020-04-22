package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Visit;
import com.springbootapps.petclinic.repositories.VisitRepository;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SDJpaService")
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;
    private final OwnerService ownerService;

    public VisitSDJpaService(VisitRepository visitRepository, OwnerService ownerService) {
        this.visitRepository = visitRepository;
        this.ownerService = ownerService;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Visit> findAll() {
        HashSet<Visit> sets = new HashSet<>();
        visitRepository.findAll().forEach(sets::add);
        return sets;
    }

    @Override
    public Visit save(Visit obj) {
        if (null != obj) {
            if (null != obj.getPet()) {
                if (null == obj.getPet().getId()) {
                    if (null != obj.getPet().getOwner()) {
                        if (null == obj.getPet().getOwner().getId()) {
                            ownerService.save(obj.getPet().getOwner());
                        }
                    } else {
                        throw new RuntimeException("Owner is must");
                    }
                }
            } else {
                throw new RuntimeException("Pet is must");
            }
        } else {
            throw new RuntimeException("Visit is must");
        }
        return visitRepository.save(obj);
    }

    @Override
    public void delete(Visit obj) {
        visitRepository.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }

    @Override
    public long count() {
        return visitRepository.count();
    }
}
