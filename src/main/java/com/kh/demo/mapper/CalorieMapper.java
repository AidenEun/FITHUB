package com.kh.demo.mapper;

import com.kh.demo.domain.dto.FoodDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalorieMapper {


    FoodDTO findByFoodName(String keyword);
}
