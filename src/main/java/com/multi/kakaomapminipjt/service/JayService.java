package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.Travel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface JayService {
    List<Travel> getJayTravelByKeyword(String keyword, int limit, int offset);
    Optional<String> findImageUrlByKeyword(String keyword);
}
