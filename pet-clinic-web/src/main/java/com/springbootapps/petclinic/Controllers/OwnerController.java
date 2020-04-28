package com.springbootapps.petclinic.Controllers;

import com.springbootapps.petclinic.model.Owner;
import com.springbootapps.petclinic.services.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @RequestMapping(value = {"", "/", "/index", "/index.html"})
    public String getList(Model model) {
        ownerService.findAll().forEach(owner -> log.info(owner.toString()));
        model.addAttribute("owners", ownerService.findAll());
        return "owners/ownersList";
    }

//    @RequestMapping(value = {"/findOwner"}, method = RequestMethod.GET)
//    public String findOwnersByLastName(@RequestParam String lastName, Model model) {
//        if (null == lastName || lastName.isEmpty()) {
//            return "redirect:/owners/index";
//        } else {
//            model.addAttribute("owners", ownerService.findByLastName(lastName));
//            return "NotImplYet";
//        }
//    }

    @GetMapping("/findOwner")
    public String processFindOwner(Owner owner, BindingResult bindingResult, Model model) {
        if (null != owner && null == owner.getLastName()) {
            owner.setLastName("");
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

    @RequestMapping({"/find"})
    public String find(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    @RequestMapping("/{id}")
    public String getOwnerDetails(@PathVariable String id, Model model) {
        Owner owner = ownerService.findById(Long.valueOf(id));
        if (null != owner) {
            model.addAttribute("owner", owner);
            return "owners/ownerDetails";
        } else {
            throw new RuntimeException(String.format("Requested Owner of id: %s is not found", id));
        }
    }
}
