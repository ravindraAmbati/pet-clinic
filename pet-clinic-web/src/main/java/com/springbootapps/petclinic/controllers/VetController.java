package com.springbootapps.petclinic.controllers;

import com.springbootapps.petclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping({"/vets", "/vets.html"})
@Controller
public class VetController {

    private final VetService vetService;

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getList(Model model) {
        vetService.findAll().forEach(vet -> log.info(vet.toString()));
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @RequestMapping("/find")
    public String getFind() {
        return "NotImplYet";
    }
}
