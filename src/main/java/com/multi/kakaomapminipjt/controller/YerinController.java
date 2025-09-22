package com.multi.kakaomapminipjt.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.kakaomapminipjt.dto.PaginatedResponse;
import com.multi.kakaomapminipjt.service.YerinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

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

    @GetMapping("/yerinTravel/searchImages")
    @ResponseBody // 이 어노테이션을 추가하여 JSON 형태로 응답하도록 지정
    public List<String> searchImages(@RequestParam String keyword) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                + "=8rwgiqAdPBI%2By6YS1Q664AimWOhgLo4ycuDqQIdPDBrInv8lEpc2QYXttxIfUqB7VdHSnAFUhbXnw46v%2FzMDew%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("TestApp", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // JSON 응답 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(sb.toString());
        List<String> imageUrls = new ArrayList<>();

        try {
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
            if (itemsNode.isArray()) {
                for (JsonNode item : itemsNode) {
                    JsonNode imageUrlNode = item.path("galWebImageUrl");
                    if (imageUrlNode.isTextual()) {
                        imageUrls.add(imageUrlNode.asText());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("JSON 파싱 오류: " + e.getMessage());
        }

        return imageUrls;
    }

}
