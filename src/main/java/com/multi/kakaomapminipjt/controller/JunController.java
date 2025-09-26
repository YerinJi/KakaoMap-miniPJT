package com.multi.kakaomapminipjt.controller;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.JunTravel;
import com.multi.kakaomapminipjt.service.JunService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JunController {

    private final JunService junService;

    @GetMapping("/junTravel")
    public String junTravel(Model model) {
        return "map/junTravel";
    }

    @GetMapping("/junTravel/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "12") int size,
                       @RequestParam(required = false) String keyword,
                       Model model){
        int total = junService.count(keyword);
        Pagination pagination = new Pagination(page, size, total, 5);

        List<JunTravel> travels = junService.list(keyword, pagination.getOffset(), pagination.getSize());

        model.addAttribute("pagination", pagination);
        model.addAttribute("travels", travels);
        model.addAttribute("keyword", keyword);

        return "map/junTravel";
    }
}
