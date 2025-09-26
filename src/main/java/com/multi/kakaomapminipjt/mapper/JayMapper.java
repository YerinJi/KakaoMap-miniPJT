package com.multi.kakaomapminipjt.mapper;

import com.multi.kakaomapminipjt.dto.Travel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JayMapper {
    List<Travel> getSearchByKeyword(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    int getCountByKeyword(@Param("keyword") String keyword);
}