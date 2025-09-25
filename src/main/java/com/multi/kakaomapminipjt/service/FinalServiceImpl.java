package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.FinalTravel;
import com.multi.kakaomapminipjt.dto.JunTravel;
import com.multi.kakaomapminipjt.mapper.FinalMapper;
import com.multi.kakaomapminipjt.mapper.JunMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinalServiceImpl implements FinalService {
    private final FinalMapper finalMapper;

    @Override
    public List<FinalTravel> list(String keyword, int offset, int limit) {
        return finalMapper.list(keyword, offset, limit);
    }

    @Override
    public int count(String district) {
        return finalMapper.count(district);
    }
}
