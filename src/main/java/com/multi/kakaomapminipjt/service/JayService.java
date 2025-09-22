package com.multi.kakaomapminipjt.service;

import com.multi.kakaomapminipjt.dto.Travel;

import java.util.List;

public interface JayService {
    List<Travel> getJayTravel();
    List<Travel> getJayTravelByKeyword();
}
