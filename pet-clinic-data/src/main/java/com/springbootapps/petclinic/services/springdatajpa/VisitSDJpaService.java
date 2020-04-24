package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Visit;
import com.springbootapps.petclinic.repositories.VisitRepository;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
@Profile("SDJpaService")
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;
    private final PetService petService;

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
        String errorMessage;
        String warningMessage;
        if (null != obj) {
            if (null != obj.getPet()) {
                if (null == obj.getPet().getId()) {
                    petService.save(obj.getPet());
                    warningMessage = "Pet is not exists with Id: " + obj.getPet().getId() + " but saved now";
                    log.warn(warningMessage);
                } else {
                    if (null != petService.findById(obj.getPet().getId())) {
                        warningMessage = "Pet is already exists with Id: " + obj.getPet().getId();
                        log.warn(warningMessage);
                    } else {
                        errorMessage = "Pet Not Found";
                        log.error(errorMessage);
                        throw new RuntimeException(errorMessage);
                    }
                }
            } else {
                errorMessage = "Pet must not be null";
                log.error(errorMessage);
                throw new RuntimeException(errorMessage);
            }
            return visitRepository.save(obj);
        } else {
            errorMessage = "Visit must not be null";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

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
