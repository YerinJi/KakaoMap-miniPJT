package com.multi.kakaomapminipjt.dto;

import com.multi.kakaomapminipjt.Pagination;
import lombok.Data;
import java.util.List;

@Data
public class PaginatedResponseYR {
    private List<YerinTravel> items;
    private Pagination pagination;

    public PaginatedResponseYR(List<YerinTravel> items, Pagination pagination) {
        this.items = items;
        this.pagination = pagination;
    }
}