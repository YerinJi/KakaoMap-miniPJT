package com.multi.kakaomapminipjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YukController {
    @GetMapping("/mapJYR")
    public String mapJYR() {
        return "YukTravel";

    }
}
