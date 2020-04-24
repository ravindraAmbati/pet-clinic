package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Profile({"default", "mapService"})
@Service
public class PetServiceMap extends AbstractMapRepository<Pet, Long> implements PetService {

    private final PetTypeService petTypeService;
    private final OwnerServiceMap ownerServiceMap;

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet obj) {
        super.delete(obj);
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
                    ownerServiceMap.save(obj.getOwner());
                    warningMessage = "Owner is not exists with Id: " + obj.getOwner().getId() + " but saved now";
                    log.warn(warningMessage);
                } else {
                    if (null != ownerServiceMap.findById(obj.getOwner().getId())) {
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
            return super.save(obj);
        } else {
            errorMessage = "Pet must not be null";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Pet findByPetType(PetType petType) {
        throw new UnsupportedOperationException("findByLastName() is not supported");
    }

    public long count() {
        return super.count();
    }
}
