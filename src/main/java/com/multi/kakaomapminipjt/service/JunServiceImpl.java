package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.JunTravel;
import com.multi.kakaomapminipjt.mapper.JunMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JunServiceImpl implements JunService{
    private final JunMapper junMapper;

    @Override
    public List<JunTravel> list(String keyword, int offset, int limit) {
        return junMapper.list(keyword, offset, limit);
    }

    @Override
    public int count(String district) {
        return junMapper.count(district);
    }
}
