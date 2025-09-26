package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.Pagination;
import com.multi.kakaomapminipjt.dto.ImageItem;
import com.multi.kakaomapminipjt.dto.PaginatedResponse;
import com.multi.kakaomapminipjt.dto.RelatedItem;
import com.multi.kakaomapminipjt.dto.YukTravel;
import com.multi.kakaomapminipjt.mapper.YukMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YukServiceImpl implements YukService {

    private final YukMapper yukMapper;
    private final TourApiService tourApiService;

    // TourApiService를 생성자를 통해 주입받습니다.
    public YukServiceImpl(YukMapper yukMapper, TourApiService tourApiService) {
        this.yukMapper = yukMapper;
        this.tourApiService = tourApiService;
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

    // Open API 호출 메서드 구현 (TourApiService 위임)
    @Override
    public List<ImageItem> getTravelImages(String contentId) {
        return tourApiService.getImages(contentId);
    }

    // Open API 호출 메서드 구현 (TourApiService 위임)
    @Override
    public List<RelatedItem> getRelatedTravels(String contentId) {
        return tourApiService.getRelatedTravels(contentId);
    }
}