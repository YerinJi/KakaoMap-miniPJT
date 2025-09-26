package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.PaginatedResponseYR;
import com.multi.kakaomapminipjt.dto.YerinTravel;
import com.multi.kakaomapminipjt.mapper.YerinMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YerinServiceImpl implements YerinService {
    private final YerinMapper yerinMapper;

    public YerinServiceImpl(YerinMapper yerinMapper) {
        this.yerinMapper = yerinMapper;
    }

    @Override
    public List<YerinTravel> getYerinTravels() {
        return yerinMapper.getYerinTravels();
    }

    @Override
    public PaginatedResponseYR searchByKeyword(String keyword, int page, int size) {
        int totalCount = yerinMapper.countByKeyword(keyword);
        Pagination pagination = new Pagination(page, size, totalCount, 5);
        List<YerinTravel> travels = yerinMapper.searchByKeyword(keyword, pagination);
        return new PaginatedResponseYR(travels, pagination);
    }
}
