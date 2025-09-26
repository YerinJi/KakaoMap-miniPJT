package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.PaginatedResponseYR;
import com.multi.kakaomapminipjt.dto.YerinTravel;

import java.util.List;

public interface YerinService {
    List<YerinTravel> getYerinTravels();
    PaginatedResponseYR searchByKeyword(String keyword, int page, int size);

}
