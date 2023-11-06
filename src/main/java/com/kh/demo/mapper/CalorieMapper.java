package com.kh.demo.mapper;

import com.kh.demo.domain.dto.ExerciseDTO;
import com.kh.demo.domain.dto.FoodDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalorieMapper {



    List<FoodDTO> findByFoodName(String keyword);

    List<FoodDTO> getTop30FoodsByViewCnt();

    FoodDTO getViewF(Long foodNum);

    void updateViewCountF(Long foodNum);

    List<ExerciseDTO> findByExecName(String keyword);

    List<ExerciseDTO> getTop30ExecsByViewCnt();

    ExerciseDTO getViewE(Long execNum);

    void updateViewCountE(Long execNum);
}
