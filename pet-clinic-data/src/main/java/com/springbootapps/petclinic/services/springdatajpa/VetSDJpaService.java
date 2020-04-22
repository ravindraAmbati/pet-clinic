package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.repositories.SpecialityRepository;
import com.springbootapps.petclinic.repositories.VetRepository;
import com.springbootapps.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@Profile("SDJpaService")
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;
    private final SpecialityRepository specialityRepository;

    public VetSDJpaService(VetRepository vetRepository, SpecialityRepository specialityRepository) {
        this.vetRepository = vetRepository;
        this.specialityRepository = specialityRepository;
    }


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
        if (null != obj) {
            obj.getSpecialities().forEach(speciality -> {
                if (null != speciality) {
                    if (null == speciality.getId()) {
                        specialityRepository.save(speciality);
                    }
                } else {
                    throw new RuntimeException("Speciality must not be null");
                }
            });
        } else {
            throw new RuntimeException("Vet must not be null");
        }
        return vetRepository.save(obj);
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