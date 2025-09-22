package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.PaginatedResponse;
import com.multi.kakaomapminipjt.dto.YerinTravel;

import java.util.List;

public interface YerinService {
    List<YerinTravel> getYerinTravels();
    PaginatedResponse searchByKeyword(String keyword, int page, int size);

}
