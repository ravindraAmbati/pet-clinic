package com.springbootapps.petclinic.controllers;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.PetType;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/owners/{ownerId}/pets")
@Controller
public class PetController {


    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private static final String PETS_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";
    private static final String REDIRECT_OWNERS = "redirect:/owners/";
    private static final String REDIRECT_OWNERS_PET_NEW = "redirect:/owners/{id}/pets/new";

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder
    public void initPetBinder(WebDataBinder dataBinder) {
//        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("petTypes")
    Set<PetType> populatePetTypes(Model model) {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    Owner findById(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @GetMapping("/new")
    public String initCreatePet(Model model) {
        model.addAttribute("pet", Pet.builder().owner((Owner) model.getAttribute("owner")).build());
        return PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/new")
    public String processCreatePet(@Valid Pet pet, BindingResult result, Model model) {
        Owner owner = (Owner) model.getAttribute("owner");
        if (null != pet && !result.hasErrors()) {
            pet.setOwner(owner);
            Pet savedPet = petService.save(pet);
            return REDIRECT_OWNERS + savedPet.getOwner().getId();
        } else {
            return String.format("redirect:/owners/%s/pets/new", owner.getId().toString());
        }
    }

    @GetMapping("/{id}/edit")
    public String initUpdatePet(@PathVariable String id, Model model) {
        Pet foundPet = Pet.builder().build();
        if (null != id) {
            foundPet = petService.findById(Long.valueOf(id));
        } else {
            return REDIRECT_OWNERS_PET_NEW;
        }
        model.addAttribute("pet", foundPet);
        return PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/{id}/edit")
    public String processUpdatePet(@PathVariable String id, @Valid Pet pet, BindingResult result, Model model) {
        Pet foundPet = Pet.builder().build();
        if (null != id) {
            foundPet = petService.findById(Long.valueOf(id));
        } else {
            return REDIRECT_OWNERS_PET_NEW;
        }
        Owner owner = (Owner) model.getAttribute("owner");
        if (null != foundPet && null != pet && !result.hasErrors()) {
            pet.setId(foundPet.getId());
            pet.setOwner(owner);
            Pet savedPet = petService.save(pet);
            return REDIRECT_OWNERS + savedPet.getOwner().getId();
        } else {
            return String.format("redirect:/owners/%s/pets/%s/edit", owner.getId().toString(), pet.getId().toString());
        }
    }
}
