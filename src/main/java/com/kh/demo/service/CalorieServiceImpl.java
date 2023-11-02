package com.kh.demo.service;

import com.kh.demo.domain.dto.FoodDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CalorieServiceImpl implements CalorieService {
    private final List<FoodDTO> foods = new ArrayList<>(); // Food 엔티티 대신 FoodDTO 사용

    @Override
    public List<FoodDTO> searchFoods(String keyword) {
        List<FoodDTO> results = new ArrayList<>();

        for (FoodDTO food : foods) {
            if (food.getFoodName().contains(keyword)) {
                results.add(food);
            }
        }

        return results;
    }
}
