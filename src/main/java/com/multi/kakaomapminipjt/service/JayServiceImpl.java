package com.multi.kakaomapminipjt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.kakaomapminipjt.dto.Travel;
import com.multi.kakaomapminipjt.mapper.JayMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JayServiceImpl implements JayService {
    private final JayMapper jayMapper;

    @Override
    public List<Travel> getJayTravelByKeyword(String keyword, int limit, int offset) {
        return jayMapper.getSearchByKeyword(keyword, limit, offset);
    }

    @Value("${tourapi.serviceKey:}")
    private String tourApiKey; // application.properties에 tourapi.serviceKey=... 로 넣어두면 자동 주입

    @Override
    public Optional<String> findImageUrlByKeyword(String keyword) {
        try {
            // 1) 필수 키 점검 (환경변수로도 fallback)
            String key = (tourApiKey == null || tourApiKey.isBlank())
                    ? System.getenv("TOURAPI_SERVICE_KEY") : tourApiKey;
            if (key == null || key.isBlank()) return Optional.empty();

            // 2) OpenAPI URL 구성
            String url = UriComponentsBuilder
                    .fromHttpUrl("http://apis.data.go.kr/B551011/PhotoGalleryService1/gallerySearchList1")
                    .queryParam("serviceKey", key)        // 인코딩은 Spring이 처리
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AppTest")
                    .queryParam("_type", "json")
                    .queryParam("numOfRows", 1)
                    .queryParam("pageNo", 1)
                    .queryParam("keyword", keyword)
                    .build(true)                           // 이미 인코딩된 key 허용
                    .toUriString();

            // 3) 호출 + 파싱
            RestTemplate rt = new RestTemplate();
            String body = rt.getForObject(url, String.class);
            if (body == null || body.isBlank()) return Optional.empty();

            ObjectMapper om = new ObjectMapper();
            JsonNode items = om.readTree(body)
                    .path("response").path("body").path("items").path("item");

            if (items.isArray() && items.size() > 0) {
                String img = items.get(0).path("galWebImageUrl").asText("");
                if (!img.isBlank()) return Optional.of(toHttps(img));
            }
        } catch (Exception e) {
            // 로깅만 하고 빈 값 반환 (서비스 죽이지 않음)
            // log.warn("Tour API error", e);
        }
        return Optional.empty();
    }

    private String toHttps(String url) {
        // 혼합콘텐츠(https 페이지에서 http 이미지) 예방
        if (url != null && url.startsWith("http://")) {
            return "https://" + url.substring("http://".length());
        }
        return url;
    }

}
