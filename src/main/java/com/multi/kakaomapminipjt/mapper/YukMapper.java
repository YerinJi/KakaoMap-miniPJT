package com.multi.kakaomapminipjt.mapper;

import com.multi.kakaomapminipjt.dto.YukTravel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface YukMapper {
    /**
     * 전체 관광지 개수 조회
     */
    int getTotalCount();

    /**
     * 페이지네이션 적용된 관광지 목록 조회
     */
    List<YukTravel> getTravelList(@Param("offset") int offset, @Param("size") int size);

    /**
     * 특정 관광지 상세 정보 조회
     */
    YukTravel getTravelByNo(int no);

    /**
     * 검색 조건에 따른 관광지 개수 조회
     */
    int getSearchCount(@Param("searchKeyword") String searchKeyword);

    /**
     * 검색 조건 및 페이지네이션 적용된 관광지 목록 조회
     */
    List<YukTravel> searchTravelList(@Param("searchKeyword") String searchKeyword, @Param("offset") int offset, @Param("size") int size);
}