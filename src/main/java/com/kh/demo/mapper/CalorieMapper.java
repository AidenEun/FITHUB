package com.kh.demo.mapper;

import com.kh.demo.domain.dto.FoodDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalorieMapper {



    List<FoodDTO> findByFoodName(String keyword);

    List<FoodDTO> getTop30FoodsByViewCnt();
}
