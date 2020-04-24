package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.services.SpecialityService;
import com.springbootapps.petclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Profile({"default", "mapService"})
@Service
public class VetServiceMap extends AbstractMapRepository<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet obj) {
        super.delete(obj);
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
            return super.save(obj);
        } else {
            errorMessage.set("Vet must not be null");
            log.error(errorMessage.get());
            throw new RuntimeException(String.valueOf(errorMessage));
        }

    }

    public long count() {
        return super.count();
    }
}
