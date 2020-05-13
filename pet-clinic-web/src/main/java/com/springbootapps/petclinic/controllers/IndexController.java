package com.springbootapps.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private static final String INDEX = "index";

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndex() {
        return INDEX;
    }
}
