//package com.multi.kakaomapminipjt.mapper;
//
//import com.multi.kakaomapminipjt.Pagination;
//import com.multi.kakaomapminipjt.dto.YerinTravel;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//
//@Mapper
//public interface YerinMapper {
//    @Select("SELECT * FROM travel")
//    List<YerinTravel> getYerinTravels();
//
//    @Select("SELECT COUNT(*) FROM travel WHERE title LIKE CONCAT('%', #{keyword}, '%') OR address LIKE CONCAT('%', #{keyword}, '%')")
//    int countByKeyword(@Param("keyword") String keyword);
//
//    @Select("SELECT * FROM travel WHERE title LIKE CONCAT('%', #{keyword}, '%') OR address LIKE CONCAT('%', #{keyword}, '%') LIMIT #{pagination.size} OFFSET #{pagination.offset}")
//    List<YerinTravel> searchByKeyword(@Param("keyword") String keyword, @Param("pagination") Pagination pagination);
//}
