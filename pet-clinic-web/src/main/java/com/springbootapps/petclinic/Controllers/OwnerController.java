package com.springbootapps.petclinic.Controllers;

import com.springbootapps.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getList(Model model) {
        System.out.println("### " + ownerService.findAll());
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
