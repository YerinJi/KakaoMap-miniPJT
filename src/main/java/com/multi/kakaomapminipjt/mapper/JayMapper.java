package com.multi.kakaomapminipjt.mapper;

import com.multi.kakaomapminipjt.dto.Travel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JayMapper {
    @Select("SELECT * FROM travel")
    List<Travel> getJayTravel();

    @Select("""
        SELECT *
        FROM travel
        WHERE title   LIKE CONCAT('%', #{keyword}, '%')
             OR address LIKE CONCAT('%', #{keyword}, '%')
        ORDER BY no DESC
        LIMIT #{limit} OFFSET #{offset}
        """)
    List<Travel> jaySearchByKeyword(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    @Select("""
        SELECT COUNT(*)
        FROM travel
        WHERE title   LIKE CONCAT('%', #{keyword}, '%')
             OR address LIKE CONCAT('%', #{keyword}, '%')
        """)
    int jayCountByKeyword(
            @Param("category") String category,
            @Param("keyword") String keyword
    );
}