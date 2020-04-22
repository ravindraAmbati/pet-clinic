package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.repositories.OwnerRepository;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SDJpaService")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetService petService, PetTypeService petTypeService) {
        this.ownerRepository = ownerRepository;
        this.petService = petService;
        this.petTypeService = petTypeService;
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
            if (null == pet.getPetType().getId()) {
                petTypeService.save(pet.getPetType());
            } else {
                if (null != petTypeService.findById(pet.getPetType().getId())) {
                    System.out.println("Pet Type is already exists with id " + pet.getPetType().getId());
                } else {
                    System.out.println("this Pet Type is not able to persist");
                }
            }
            if (null == pet.getId()) {
                petService.save(pet);
            } else {
                if (null != petService.findById(pet.getId())) {
                    System.out.println("Pet is already exists with id " + pet.getId());
                } else {
                    System.out.println("this Pet is not able to persist");
                }
            }
        });
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
    public long count() {
        return ownerRepository.count();
    }
}
