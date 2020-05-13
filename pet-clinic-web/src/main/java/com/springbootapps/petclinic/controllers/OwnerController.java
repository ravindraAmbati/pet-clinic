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
    private static final String OWNERS_LIST = "owners/ownersList";
    private static final String REDIRECT_OWNERS = "redirect:/owners/";
    private static final String REDIRECT_OWNERS_FIND = "redirect:/owners/find";
    private static final String FIND_OWNERS = "owners/findOwners";
    private static final String OWNERS_DETAILS = "owners/ownerDetails";
    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_NEW = "redirect:/owners/new";

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = {"", "/", "/index", "/index.html"})
    public String getList(Model model) {
        ownerService.findAll().forEach(owner -> log.info(owner.toString()));
        model.addAttribute("owners", ownerService.findAll());
        return OWNERS_LIST;
    }

    @GetMapping("/findOwner")
    public String processFindOwner(Owner owner, BindingResult bindingResult, Model model) {
        if (null != owner && (null == owner.getLastName() || owner.getLastName().isEmpty())) {
            return REDIRECT_OWNERS;
        }
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());
        if (results.size() > 1) {
            model.addAttribute("owners", results);
            return OWNERS_LIST;
        } else if (results.size() == 1) {
            return REDIRECT_OWNERS + results.get(0).getId();
        } else {
            bindingResult.rejectValue("lastName", "notFound", "Not Found");
            return REDIRECT_OWNERS_FIND;
        }
    }

    @GetMapping({"/find"})
    public String find(Model model) {
        model.addAttribute("owner", new Owner());
        return FIND_OWNERS;
    }

    @GetMapping("/{id}")
    public String getOwnerDetails(@PathVariable String id, Model model) {
        Owner owner = ownerService.findById(Long.valueOf(id));
        if (null != owner) {
            model.addAttribute("owner", owner);
            return OWNERS_DETAILS;
        } else {
            throw new RuntimeException(String.format("Requested Owner of id: %s is not found", id));
        }
    }


    @GetMapping("/new")
    public String initCreateOwner(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processCreateOwner(@Valid Owner owner, BindingResult result) {
        if (null != owner && !result.hasErrors()) {
            Owner savedOwner = ownerService.save(owner);
            return REDIRECT_OWNERS + savedOwner.getId();
        } else {
            return REDIRECT_OWNERS_NEW;
        }
    }

    @GetMapping("/{id}/edit")
    public String initUpdateOwner(@PathVariable String id, Model model) {
        Owner foundOwner = ownerService.findById(Long.valueOf(id));
        if (null != foundOwner) {
            model.addAttribute("owner", foundOwner);
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            return REDIRECT_OWNERS_NEW;
        }
    }

    @PostMapping("/{id}/edit")
    public String processUpdateOwner(@PathVariable String id, @Valid Owner owner, BindingResult result) {
        Owner foundOwner = ownerService.findById(Long.valueOf(id));
        if (null != foundOwner && null != owner && !result.hasErrors()) {
            owner.setId(foundOwner.getId());
            Owner savedOwner = ownerService.save(owner);
            return REDIRECT_OWNERS + savedOwner.getId();
        } else {
            return REDIRECT_OWNERS_NEW;
        }
    }
}
