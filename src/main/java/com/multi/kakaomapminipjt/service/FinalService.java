package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.FinalTravel;
import com.multi.kakaomapminipjt.dto.JunTravel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FinalService {
    List<FinalTravel> list(String keyword, int offset, int limit);
    int count(@Param("district") String district);
}
