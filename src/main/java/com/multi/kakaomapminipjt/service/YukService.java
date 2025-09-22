package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.PaginatedResponse;
import com.multi.kakaomapminipjt.dto.YukTravel;

public interface YukService {

    /**
     * 페이지네이션 적용된 관광지 목록을 가져옵니다.
     */
    PaginatedResponse getTravelList(int page, int size, int block);

    /**
     * 관광지 상세 정보를 가져옵니다.
     */
    YukTravel getTravelDetail(int no);

    /**
     * 검색 결과에 대해 페이지네이션 적용된 목록을 가져옵니다.
     */
    PaginatedResponse searchTravelList(String searchKeyword, int page, int size, int block);
}