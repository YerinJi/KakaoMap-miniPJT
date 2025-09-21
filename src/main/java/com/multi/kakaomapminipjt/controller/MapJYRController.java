package com.multi.kakaomapminipjt.controller;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.Travel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MapJYRController {
    @GetMapping("/mapJYR")
    public String mapJYR() {
        return "map/mapJYR";

    }

}
