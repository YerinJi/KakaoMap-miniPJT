package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.PaginatedResponse;
import com.multi.kakaomapminipjt.dto.YukTravel;
import com.multi.kakaomapminipjt.mapper.YukMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YukServiceImpl implements YukService {

    private final YukMapper yukMapper;
    private final int blockSize = 10; // 페이지 블록 크기

    public YukServiceImpl(YukMapper yukMapper) {
        this.yukMapper = yukMapper;
    }

    @Override
    public PaginatedResponse getTravelList(int page, int size, int block) {
        int total = yukMapper.getTotalCount();
        Pagination pagination = new Pagination(page, size, total, block);
        int offset = pagination.getOffset();

        List<YukTravel> items = yukMapper.getTravelList(offset, size);

        return new PaginatedResponse(items, pagination);
    }

    @Override
    public YukTravel getTravelDetail(int no) {
        return yukMapper.getTravelByNo(no);
    }

    @Override
    public PaginatedResponse searchTravelList(String searchKeyword, int page, int size, int block) {
        int total = yukMapper.getSearchCount(searchKeyword);
        Pagination pagination = new Pagination(page, size, total, block);
        int offset = pagination.getOffset();

        List<YukTravel> items = yukMapper.searchTravelList(searchKeyword, offset, size);

        return new PaginatedResponse(items, pagination);
    }
}