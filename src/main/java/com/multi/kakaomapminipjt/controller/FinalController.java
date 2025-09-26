package com.multi.kakaomapminipjt.controller;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.FinalTravel;
import com.multi.kakaomapminipjt.dto.JunTravel;
import com.multi.kakaomapminipjt.service.FinalService;
import com.multi.kakaomapminipjt.service.JunService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FinalController {
    private final FinalService finalService;

    @GetMapping("/finalTravel")
    public String junTravel(Model model) {
        return "map/finalTravel";
    }

    @GetMapping("/finalTravel/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "12") int size,
                       @RequestParam(required = false) String keyword,
                       Model model){
        int total = finalService.count(keyword);
        Pagination pagination = new Pagination(page, size, total, 5);

        List<FinalTravel> travels = finalService.list(keyword, pagination.getOffset(), pagination.getSize());

        model.addAttribute("pagination", pagination);
        model.addAttribute("travels", travels);
        model.addAttribute("keyword", keyword);

        return "map/finalTravel";
    }
}
