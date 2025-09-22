package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.JayTravel;
import com.multi.kakaomapminipjt.mapper.JayMapper;

import java.util.List;

public class JayServiceImpl implements JayService {
    private final JayMapper jayMapper;

    public JayServiceImpl(JayMapper jayMapper) {
        this.jayMapper = jayMapper;
    }

    @Override
    public List<JayTravel> getJayTravel() {
        return jayMapper.getJayTravel();
    }

    @Override
    public List<JayTravel> getJayTravelByKeyword() {
        return List.of();
    }
}
