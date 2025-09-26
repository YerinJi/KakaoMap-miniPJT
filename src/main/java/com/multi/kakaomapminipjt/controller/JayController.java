package com.multi.kakaomapminipjt.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.Travel;
import com.multi.kakaomapminipjt.mapper.JayMapper;
import com.multi.kakaomapminipjt.service.JayService;
import com.multi.kakaomapminipjt.service.JayServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class JayController {
    private final JayService jayService;
    private final JayMapper jayMapper;
    private final JayServiceImpl jayServiceImpl;

    @GetMapping("/jayTravel")
    public String mapJYR() {
        return "map/jayTravel";
    }

    @GetMapping("/jayTravel/search")
    public String searchJayTravel(@RequestParam String keyword,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model) {
        int total = jayMapper.getCountByKeyword(keyword);
        Pagination pagination = new Pagination(page, size, total, 5);

        List<Travel> travels = jayServiceImpl.getJayTravelByKeyword(
                keyword,
                pagination.getSize(), //limit
                pagination.getOffset() //offset
        );
        model.addAttribute("travels", travels);
        model.addAttribute("pagination", pagination);
        model.addAttribute("keyword", keyword);
        return "map/jayTravel";
    }
}