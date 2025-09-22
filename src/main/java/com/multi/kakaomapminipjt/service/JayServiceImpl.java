package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.Travel;
import com.multi.kakaomapminipjt.mapper.JayMapper;

import java.util.List;

public class JayServiceImpl implements JayService {
    private final JayMapper jayMapper;

    public JayServiceImpl(JayMapper jayMapper) {
        this.jayMapper = jayMapper;
    }

    @Override
    public List<Travel> getJayTravel() {
        return jayMapper.getJayTravel();
    }

    @Override
    public List<Travel> getJayTravelByKeyword() {
        return jayMapper.jaySearchByKeyword();
    }
}
