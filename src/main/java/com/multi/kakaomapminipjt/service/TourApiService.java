package com.multi.kakaomapminipjt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.kakaomapminipjt.dto.ImageItem;
import com.multi.kakaomapminipjt.dto.RelatedItem;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TourApiService {

    private static final String SERVICE_KEY = "9be4f56fc9949cb9ae53858e18c49a3825aa485aed2fbea968b08907c10a8657";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String BASE_URL = "http://apis.data.go.kr/B551011";
    private static final String SAMPLE_CONTENT_ID = "126508";

    public List<ImageItem> getImages(String contentId) {
        String id = contentId != null && !contentId.isEmpty() ? contentId : SAMPLE_CONTENT_ID;

        try {
            // 최종 엔드포인트: KorService2/detailImage
            String url = String.format("%s/KorService2/detailImage?serviceKey=%s&contentId=%s&imageYN=Y&subImageYN=Y&numOfRows=5&pageNo=1&_type=json",
                    BASE_URL, encode(SERVICE_KEY), id);

            JsonNode root = executeApiCall(url);
            JsonNode itemNode = root.path("response").path("body").path("items").path("item");

            if (itemNode.isArray() && itemNode.size() > 0) {
                return StreamSupport.stream(itemNode.spliterator(), false)
                        .map(node -> {
                            ImageItem item = new ImageItem();
                            item.setOriginimgurl(node.path("originimgurl").asText());
                            item.setSmallimageurl(node.path("smallimageurl").asText());
                            item.setContentid(node.path("contentid").asText());
                            return item;
                        })
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            System.err.println("Error fetching images: " + e.getMessage());
            // API 오류 발생 시 Mock 데이터 반환 (상세 페이지 로딩 유지)
            return List.of(
                    createSampleImageItem("https://via.placeholder.com/200x150?text=API+Error+Mockup+1"),
                    createSampleImageItem("https://via.placeholder.com/200x150?text=API+Error+Mockup+2")
            );
        }
        return Collections.emptyList();
    }

    public List<RelatedItem> getRelatedTravels(String contentId) {
        return List.of(
                createSampleRelatedItem("인근 관광지 A", "서울시 강남구 삼성동"),
                createSampleRelatedItem("인근 관광지 B", "서울시 서초구 서초동")
        );
    }

    private RelatedItem createSampleRelatedItem(String title, String addr) {
        RelatedItem item = new RelatedItem();
        item.setTitle(title);
        item.setAddr1(addr);
        return item;
    }

    private ImageItem createSampleImageItem(String url) {
        ImageItem item = new ImageItem();
        item.setOriginimgurl(url);
        item.setSmallimageurl(url);
        return item;
    }

    private JsonNode executeApiCall(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("API Call failed: " + response.statusCode() + " | " + response.body());
        }

        return objectMapper.readTree(response.body());
    }

    private String encode(String key) throws UnsupportedEncodingException {
        return URLEncoder.encode(key, "UTF-8");
    }
}