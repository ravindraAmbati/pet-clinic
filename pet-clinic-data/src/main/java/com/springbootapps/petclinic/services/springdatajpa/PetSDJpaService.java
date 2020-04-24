package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.repositories.PetRepository;
import com.springbootapps.petclinic.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Profile("SDJpaService")
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    @Override
    public Pet findByPetType(PetType petType) {
        return petRepository.findByPetType(petType).orElse(null);
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Pet> findAll() {
        HashSet<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet save(Pet obj) {
        return petRepository.save(obj);
    }

    @Override
    public void delete(Pet obj) {
        petRepository.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }

    @Override
    public long count() {
        return petRepository.count();
    }
}
