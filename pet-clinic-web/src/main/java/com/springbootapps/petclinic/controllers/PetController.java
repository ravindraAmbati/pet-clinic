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

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/owners/{id}/pets")
@Controller
public class PetController {

    private static final String VIEW_PET_CREATION_FORM = "pets/createOrUpdatePetForm";
    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

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
    Owner findById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @GetMapping("/new")
    public String initCreatePet(Model model) {
        model.addAttribute("pet", Pet.builder().owner((Owner) model.getAttribute("owner")).build());
        return VIEW_PET_CREATION_FORM;
    }

    @PostMapping("/new")
    public String processCreatePet(Pet pet, BindingResult result, Model model) {
        Owner owner = (Owner) model.getAttribute("owner");
        if (null != pet && !result.hasErrors()) {
            pet.setOwner(owner);
            Pet savedPet = petService.save(pet);
            return "redirect:/owners/" + savedPet.getOwner().getId();
        } else {
            return String.format("redirect:/owners/%s/pets/new", owner.getId().toString());
        }
    }
}
