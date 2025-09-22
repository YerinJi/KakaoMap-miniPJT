package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.JayTravel;

import java.util.List;

public interface JayService {
    List<JayTravel> getJayTravel();
    List<JayTravel> getJayTravelByKeyword();
}
