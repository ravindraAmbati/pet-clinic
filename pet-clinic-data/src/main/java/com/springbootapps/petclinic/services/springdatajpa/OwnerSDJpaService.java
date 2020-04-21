package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.repositories.OwnerRepository;
import com.springbootapps.petclinic.repositories.PetRepository;
import com.springbootapps.petclinic.repositories.PetTypeRepository;
import com.springbootapps.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
@Profile("SDJpaService")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName).orElse(null);
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Owner> findAll() {
        HashSet<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner save(Owner obj) {
        obj.getPets().forEach(pet -> {
            petTypeRepository.save(pet.getPetType());
        });
        Iterator<Pet> iterator = obj.getPets().iterator();
        while (iterator.hasNext()) {
            Pet tempPet = (Pet) iterator.next();
            petRepository.save(tempPet);
        }
        return ownerRepository.save(obj);
    }

    @Override
    public void delete(Owner obj) {
        ownerRepository.save(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }

    @Override
    public int getSize() {
        return (int) ownerRepository.count();
    }
}
