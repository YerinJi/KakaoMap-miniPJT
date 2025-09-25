package com.multi.kakaomapminipjt.dto;

import lombok.Data;
import java.util.List;

@Data
public class PaginatedResponse {
    private List<YukTravel> items;
    private Pagination pagination;

    public PaginatedResponse(List<YukTravel> items, Pagination pagination) {
        this.items = items;
        this.pagination = pagination;
    }
}