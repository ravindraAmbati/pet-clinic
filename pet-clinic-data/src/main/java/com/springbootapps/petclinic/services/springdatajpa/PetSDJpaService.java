package com.springbootapps.petclinic.services.springdatajpa;

import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.repositories.PetRepository;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.PetTypeService;
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
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

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
        String errorMessage;
        String warningMessage;
        if (null != obj) {
            if (null != obj.getPetType()) {
                if (null == obj.getPetType().getId()) {
                    petTypeService.save(obj.getPetType());
                    warningMessage = "PetType is not exists with Id: " + obj.getPetType().getId() + " but saved now";
                    log.warn(warningMessage);
                } else {
                    if (null != petTypeService.findById(obj.getPetType().getId())) {
                        warningMessage = "PetType is already exists with Id: " + obj.getPetType().getId();
                        log.warn(warningMessage);
                    } else {
                        errorMessage = "PetType Not Found";
                        log.error(errorMessage);
                        throw new RuntimeException(errorMessage);
                    }
                }
            } else {
                errorMessage = "PetType must not be null";
                log.error(errorMessage);
                throw new RuntimeException(errorMessage);
            }
            if (null != obj.getOwner()) {
                if (null == obj.getOwner().getId()) {
                    ownerService.save(obj.getOwner());
                    warningMessage = "Owner is not exists with Id: " + obj.getOwner().getId() + " but saved now";
                    log.warn(warningMessage);
                } else {
                    if (null != ownerService.findById(obj.getOwner().getId())) {
                        warningMessage = "Owner is already exists with Id: " + obj.getOwner().getId();
                        log.warn(warningMessage);
                    } else {
                        errorMessage = "Owner Not Found";
                        log.error(errorMessage);
                        throw new RuntimeException(errorMessage);
                    }
                }
            } else {
                errorMessage = "Owner must not be null";
                log.error(errorMessage);
                throw new RuntimeException(errorMessage);
            }
            return petRepository.save(obj);
        } else {
            errorMessage = "Pet must not be null";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
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
