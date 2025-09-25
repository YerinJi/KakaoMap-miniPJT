package com.multi.kakaomapminipjt.dto;

import lombok.Data;

@Data
public class ImageItem {
    private String originimgurl; // 원본 이미지 URL (관광사진 정보_GW)
    private String smallimageurl; // 작은 이미지 URL
    private String contentid;
    // 필요한 다른 필드를 추가할 수 있습니다.
}