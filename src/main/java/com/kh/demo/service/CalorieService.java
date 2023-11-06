package com.kh.demo.service;

import com.kh.demo.domain.dto.ExerciseDTO;
import com.kh.demo.domain.dto.FoodDTO;
import com.kh.demo.mapper.CalorieMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CalorieService {
    

    List<FoodDTO> getTop30Foods();

    List<FoodDTO> searchFoods(String keyword);


    FoodDTO foodViewF(Long foodNum);

    void updateViewCountF(Long foodNum);
    List<ExerciseDTO> getTop30execs();

    List<ExerciseDTO> searchExecs(String keyword);


    ExerciseDTO execViewE(Long exerNum);


    void updateViewCountE(Long execNum);

    List<FoodDTO> getFindFood(String keyword);
}
