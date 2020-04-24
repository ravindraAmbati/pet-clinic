package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.repositories.VetRepository;
import com.springbootapps.petclinic.services.SpecialityService;
import com.springbootapps.petclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Service
@Profile("SDJpaService")
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;
    private final SpecialityService specialityService;

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Vet> findAll() {
        HashSet<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet save(Vet obj) {
        AtomicReference<String> errorMessage = new AtomicReference<>();
        AtomicReference<String> warningMessage = new AtomicReference<>();
        if (null != obj) {
            if (null != obj.getSpecialities()) {
                obj.getSpecialities().forEach(speciality -> {
                    if (null == speciality.getId()) {
                        specialityService.save(speciality);
                    } else {
                        if (null != specialityService.findById(speciality.getId())) {
                            warningMessage.set("Speciality is already exists");
                            log.warn(warningMessage.get());
                        } else {
                            errorMessage.set("Speciality Not Found");
                            log.error(errorMessage.get());
                            throw new RuntimeException(String.valueOf(errorMessage));
                        }
                    }
                });
            } else {
                warningMessage.set("Speciality is not mentioned");
                log.warn(warningMessage.get());
            }
            return vetRepository.save(obj);
        } else {
            errorMessage.set("Vet must not be null");
            log.error(errorMessage.get());
            throw new RuntimeException(String.valueOf(errorMessage));
        }

    }

    @Override
    public void delete(Vet obj) {
        vetRepository.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRepository.deleteById(aLong);
    }

    @Override
    public long count() {
        return vetRepository.count();
    }
}
