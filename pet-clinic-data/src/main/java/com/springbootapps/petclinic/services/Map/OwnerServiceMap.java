package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile("mapService")
@Service
public class OwnerServiceMap extends AbstractMapRepository<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

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
        if (null != obj) {
            if (null == obj.getId()) {
                obj.setId(super.getNextId());
                if (null != obj.getPets() && obj.getPets().size() > 0) {
                    obj.getPets().forEach(pet -> {
                        if (null != pet) {
                            if (null != pet.getPetType()) {
                                PetType tempPetType = pet.getPetType();
                                if (null == tempPetType.getId()) {
                                    petTypeService.save(tempPetType);
                                } else {
                                    System.out.println(" Pet Type is already exits with id " + tempPetType.getId());
                                }
                            } else {
                                throw new RuntimeException("At least of one PetType is mandatory");
                            }
                            if (null == pet.getId()) {
                                petService.save(pet);
                            } else {
                                System.out.println(" Pet is already exits with id " + pet.getId());
                            }

                        } else {
                            throw new RuntimeException("Pet must not be null");
                        }
                    });
                } else {
                    throw new RuntimeException("At least of one Pet is mandatory");
                }
            }
            return super.save(obj);
        } else {
            throw new RuntimeException("Owner must not be null");
        }
    }

    @Override
    public Owner findByLastName(String lastName) {
        throw new UnsupportedOperationException("findByLastName() is not supported");
    }

    public int getSize() {
        return super.getSize();
    }
}
