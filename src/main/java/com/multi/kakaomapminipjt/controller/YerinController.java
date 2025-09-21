package com.multi.kakaomapminipjt.controller;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.JunTravel;
import com.multi.kakaomapminipjt.dto.PaginatedResponse;
import com.multi.kakaomapminipjt.dto.YerinTravel;
import com.multi.kakaomapminipjt.mapper.YerinMapper;
import com.multi.kakaomapminipjt.service.YerinService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class YerinController {
    private final YerinService yerinService;

    public YerinController(YerinService yerinService) {
        this.yerinService = yerinService;
    }

    @GetMapping("/yerinTravel")
    public String mapJYR() {
        return "map/yerinTravel";
    }

    @GetMapping("/yerinTravel/search")
    @ResponseBody
    public PaginatedResponse searchYerinTravels(@RequestParam String keyword,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return yerinService.searchByKeyword(keyword, page, size);
    }

}
