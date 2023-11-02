package com.kh.demo.service;

import com.kh.demo.domain.dto.FoodDTO;
import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.mapper.CalorieMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CalorieServiceImpl implements CalorieService {

    CalorieMapper calmapper;


    private final List<FoodDTO> foods = new ArrayList<>();


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

    @Override
    public List<String> getSuggestedFoods(String keyword) {
        List<FoodDTO> suggestedFoodDTOs = (List<FoodDTO>) calmapper.findByFoodName(keyword);

        List<String> suggestedFoods = new ArrayList<>();

        for (FoodDTO foodDTO : suggestedFoodDTOs) {
            suggestedFoods.add(foodDTO.getFoodName());
        }

        return suggestedFoods;
    }


}
