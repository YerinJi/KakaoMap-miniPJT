package com.multi.kakaomapminipjt.dto;

import com.multi.kakaomapminipjt.Pagination;
import lombok.Data;
import java.util.List;

@Data
public class PaginatedResponse {
    private List<YerinTravel> items;
    private Pagination pagination;

    public PaginatedResponse(List<YerinTravel> items, Pagination pagination) {
        this.items = items;
        this.pagination = pagination;
    }
}