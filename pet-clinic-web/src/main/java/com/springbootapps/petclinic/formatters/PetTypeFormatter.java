package com.springbootapps.petclinic.formatters;

import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        PetType foundPetType = new PetType();
        petTypeService.findAll().forEach(petType -> {
            if (petType.getName().equals(s)) {
                foundPetType.setId(petType.getId());
                foundPetType.setName(petType.getName());
                return;
            }
        });
        return foundPetType;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
