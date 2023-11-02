package com.kh.demo.service;

import com.kh.demo.domain.dto.FoodDTO;

import java.util.List;

public interface CalorieService {
    List<FoodDTO> searchFoods(String keyword);

    List<String> getSuggestedFoods(String keyword);


}
