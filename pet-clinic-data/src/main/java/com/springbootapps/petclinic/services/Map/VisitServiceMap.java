package com.springbootapps.petclinic.services.Map;

import com.springbootapps.petclinic.model.Visit;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Profile({"default", "mapService"})
@Service
public class VisitServiceMap extends AbstractMapRepository<Visit, Long> implements VisitService {

    private final PetService petService;

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit obj) {
        super.delete(obj);
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
            return super.save(obj);
        } else {
            errorMessage = "Visit must not be null";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

    }
}
