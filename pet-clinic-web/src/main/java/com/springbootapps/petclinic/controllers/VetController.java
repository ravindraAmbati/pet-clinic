package com.springbootapps.petclinic.controllers;

import com.springbootapps.petclinic.model.Vet;
import com.springbootapps.petclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RequestMapping({"/vets", "/vets.html"})
@Controller
public class VetController {

    private final VetService vetService;
    private static final String NOT_IMPL_YET = "NotImplYet";
    private static final String VETS_INDEX = "vets/index";

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getList(Model model) {
        vetService.findAll().forEach(vet -> log.info(vet.toString()));
        model.addAttribute("vets", vetService.findAll());
        return VETS_INDEX;
    }

    @RequestMapping("/api")
    public @ResponseBody
    Set<Vet> getJson() {
        return vetService.findAll();
    }

    @RequestMapping("/find")
    public String getFind() {
        return NOT_IMPL_YET;
    }
}
