package com.cepestm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlller {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
