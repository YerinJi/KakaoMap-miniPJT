package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.JunTravel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JunService {
    List<JunTravel> list(String keyword, int offset, int limit);
    int count(@Param("district") String district);
}
