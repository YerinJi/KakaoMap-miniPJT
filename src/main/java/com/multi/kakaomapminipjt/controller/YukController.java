package com.multi.kakaomapminipjt.controller;

import com.multi.kakaomapminipjt.dto.PaginatedResponse;
import com.multi.kakaomapminipjt.dto.YukTravel;
import com.multi.kakaomapminipjt.service.YukService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YukController {

    private final YukService yukService;
    private final int pageSize = 10; // 한 페이지당 게시물 수
    private final int block = 5;     // 페이지네이션 블록 크기

    public YukController(YukService yukService) {
        this.yukService = yukService;
    }

    /**
     * 관광지 목록 조회 (페이지네이션 및 검색 포함)
     */
    @GetMapping("/YukTravel")
    public String getTravelList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "search", required = false) String searchKeyword,
            Model model
    ) {
        PaginatedResponse response;

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            // 검색하여 페이지별 목록보기
            response = yukService.searchTravelList(searchKeyword.trim(), page, pageSize, block);
        } else {
            // 관광지 페이지별 목록보기
            response = yukService.getTravelList(page, pageSize, block);
        }

        model.addAttribute("response", response);
        model.addAttribute("searchKeyword", searchKeyword); // 검색어 유지
        return "map/YukTravel"; // templates/map/YukTravel.html 렌더링
    }

    /**
     * 관광지 상세 보기
     * 상세 보기에서는 Open API 및 카카오맵 연동이 필요합니다.
     */
    @GetMapping("/YukTravelDetail")
    public String getTravelDetail(
            @RequestParam("no") int no,
            Model model
    ) {
        YukTravel travel = yukService.getTravelDetail(no);
        model.addAttribute("travel", travel);

        // TODO:
        // 1. 한국관광공사 Open API를 활용하여 이미지 정보, 주변 관광지 정보를 가져와 Model에 추가
        // 2. 카카오 지도 연동을 위한 **주소 정보 (travel.getAddress())**를 Model에 전달

        return "map/YukTravelDetail"; // templates/map/YukTravelDetail.html (추가 필요) 렌더링
    }
}