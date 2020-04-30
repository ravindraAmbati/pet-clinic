package com.springbootapps.petclinic.controllers;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.services.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = {"", "/", "/index", "/index.html"})
    public String getList(Model model) {
        ownerService.findAll().forEach(owner -> log.info(owner.toString()));
        model.addAttribute("owners", ownerService.findAll());
        return "owners/ownersList";
    }

    @GetMapping("/findOwner")
    public String processFindOwner(Owner owner, BindingResult bindingResult, Model model) {
        if (null != owner && (null == owner.getLastName() || owner.getLastName().isEmpty())) {
            return "redirect:/owners/";
        }
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());
        if (results.size() > 1) {
            model.addAttribute("owners", results);
            return "owners/ownersList";
        } else if (results.size() == 1) {
            return "redirect:/owners/" + results.get(0).getId();
        } else {
            bindingResult.rejectValue("lastName", "notFound", "Not Found");
            return "redirect:/owners/find";
        }
    }

    @GetMapping({"/find"})
    public String find(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping("/{id}")
    public String getOwnerDetails(@PathVariable String id, Model model) {
        Owner owner = ownerService.findById(Long.valueOf(id));
        if (null != owner) {
            model.addAttribute("owner", owner);
            return "owners/ownerDetails";
        } else {
            throw new RuntimeException(String.format("Requested Owner of id: %s is not found", id));
        }
    }


    @GetMapping("/new")
    public String initCreateOwner(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreateOwner(@Valid Owner owner, BindingResult result) {
        if (null != owner && !result.hasErrors()) {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        } else {
            return "redirect:/owners/new";
        }
    }

    @GetMapping("/{id}/edit")
    public String initUpdateOwner(@PathVariable String id, Model model) {
        Owner foundOwner = ownerService.findById(Long.valueOf(id));
        if (null != foundOwner) {
            model.addAttribute("owner", foundOwner);
            return "owners/createOrUpdateOwnerForm";
        } else {
            return "redirect:/owners/new";
        }
    }

    @PostMapping("/{id}/edit")
    public String processUpdateOwner(@PathVariable String id, @Valid Owner owner, BindingResult result) {
        Owner foundOwner = ownerService.findById(Long.valueOf(id));
        if (null != foundOwner && null != owner && !result.hasErrors()) {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        } else {
            return "redirect:/owners/new";
        }
    }
}
