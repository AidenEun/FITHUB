package com.kh.demo.service;

import com.kh.demo.domain.dto.FoodDTO;
import com.kh.demo.mapper.CalorieMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CalorieService {
    

    List<FoodDTO> getTop30Foods();

    List<FoodDTO> searchFoods(String keyword);


    FoodDTO foodView(Long foodNum);

    void updateViewCount(Long foodNum);
}
