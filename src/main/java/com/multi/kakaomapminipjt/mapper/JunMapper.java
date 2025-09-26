package com.multi.kakaomapminipjt.mapper;

import com.multi.kakaomapminipjt.dto.JunTravel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JunMapper {
    List<JunTravel> list(@Param("keyword") String keyword,
                         @Param("offset") int offset,
                         @Param("limit") int limit
                         );
    int count(@Param("keyword") String keyword);
}
