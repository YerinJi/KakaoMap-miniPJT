package com.multi.kakaomapminipjt.controller;

import com.multi.kakaomapminipjt.dto.ImageItem;
import com.multi.kakaomapminipjt.dto.PaginatedResponse;
import com.multi.kakaomapminipjt.dto.RelatedItem;
import com.multi.kakaomapminipjt.dto.YukTravel;
import com.multi.kakaomapminipjt.service.YukService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class YukController {

    private final YukService yukService;
    private final int pageSize = 10;
    private final int block = 5;

    public YukController(YukService yukService) {
        this.yukService = yukService;
    }

    /**
     * 100대 관광지 목록 보기 및 검색 (페이지별 목록 보기 및 검색하여 페이지별 목록 보기)
     */
    @GetMapping("/YukTravel")
    public String getTravelList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "search", required = false) String searchKeyword,
            Model model
    ) {
        PaginatedResponse response;

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            response = yukService.searchTravelList(searchKeyword.trim(), page, pageSize, block);
        } else {
            response = yukService.getTravelList(page, pageSize, block);
        }

        model.addAttribute("response", response);
        model.addAttribute("searchKeyword", searchKeyword);
        return "map/YukTravel";
    }

    /**
     * 관광지 상세 보기 (이미지, 지도, 주변 관광지 포함)
     */
    @GetMapping("/YukTravelDetail")
    public String getTravelDetail(
            @RequestParam("no") int no,
            Model model
    ) {
        // 1. 100대 관광지 DB 정보 가져오기
        YukTravel travel = yukService.getTravelDetail(no);

        if (travel == null) {
            return "redirect:/YukTravel";
        }

        model.addAttribute("travel", travel);

        // 2. Open API 활용하여 이미지 및 주변 관광지 정보 가져오기
        String contentId = travel.getContentId();

        // 해당 관광지의 이미지 표시하기
        List<ImageItem> images = yukService.getTravelImages(contentId);
        model.addAttribute("images", images);

        // 주변 관광지 정보 표시하기
        List<RelatedItem> nearbyTravels = yukService.getRelatedTravels(contentId);
        model.addAttribute("nearbyTravels", nearbyTravels);

        // 카카오 지도에 관광지 표시하기는 View에서 주소 정보를 이용해 처리합니다.
        return "map/YukTravelDetail";
    }
}