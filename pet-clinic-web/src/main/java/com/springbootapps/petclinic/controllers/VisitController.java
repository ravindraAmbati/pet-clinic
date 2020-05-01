package com.springbootapps.petclinic.controllers;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.model.Pet;
import com.springbootapps.petclinic.model.Visit;
import com.springbootapps.petclinic.services.OwnerService;
import com.springbootapps.petclinic.services.PetService;
import com.springbootapps.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
@Controller
public class VisitController {

    private static final String VIEW = "visits/createOrUpdateVisitForm";

    private final OwnerService ownerService;
    private final PetService petService;
    private final VisitService visitService;

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("owner")
    Owner findOwnerById(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("pet")
    Pet findPetById(@PathVariable Long petId) {
        return petService.findById(petId);
    }

    @GetMapping("/new")
    public String initCreatePet(Model model) {
        Pet pet = (Pet) model.getAttribute("Pet");
        model.addAttribute("visit", Visit.builder().pet(pet).build());
        return VIEW;
    }

    @PostMapping("/new")
    public String processCreatePet(@Valid Visit visit, BindingResult result, Model model) {
        Pet pet = (Pet) model.getAttribute("pet");
        if (null != visit && !result.hasErrors()) {
            visit.setPet(pet);
            Visit savedVisit = visitService.save(visit);
            return "redirect:/owners/" + savedVisit.getPet().getOwner().getId();
        } else {
            return String.format("redirect:/owners/%s/pets/%s/visits/new", pet.getOwner().getId().toString(), pet.getId());
        }
    }

}
