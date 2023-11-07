package com.kh.demo.service;

import com.kh.demo.domain.dto.ExerciseDTO;
import com.kh.demo.domain.dto.FoodDTO;
import com.kh.demo.mapper.CalorieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Qualifier("CalorieServiceImpl")
public class CalorieServiceImpl implements CalorieService {
    @Autowired
    CalorieMapper calmapper;

    public List<FoodDTO> searchFoods(String keyword) {
        List<FoodDTO> databaseResults = (List<FoodDTO>) calmapper.findByFoodName(keyword);

        if (databaseResults == null || databaseResults.isEmpty()) {
            return new ArrayList<>();
        }

        // 각 항목의 연관성 점수를 계산하고 설정
        for (FoodDTO food : databaseResults) {
            double relevanceScore = calculateRelevanceScore(food, keyword);
            food.setRelevanceScore(relevanceScore);
        }

        // 연관성 점수에 따라 내림차순으로 정렬
        databaseResults.sort((food1, food2) ->
                Double.compare(food2.getRelevanceScore(), food1.getRelevanceScore())
        );

        // 상위 30개 항목 선택
        int limit = Math.min(databaseResults.size(), 30);
        return databaseResults.subList(0, limit);
    }

    private double calculateRelevanceScore(FoodDTO food, String keyword) {
        String foodName = food.getFoodName().toLowerCase(); // 검색 결과의 식품 이름
        keyword = keyword.toLowerCase(); // 입력 키워드

        // 키워드와 식품 이름 사이의 연관성을 계산하는 간단한 방법
        // 예를 들어, 키워드가 식품 이름에 포함되면 연관성이 높다고 가정
        if (foodName.contains(keyword)) {
            return 1.0; // 연관성이 높음
        } else {
            return 0.0; // 연관성이 낮음
        }
    }

    @Override
    public List<FoodDTO> getTop30Foods() {
        List<FoodDTO> foodDTOs = calmapper.getTop30FoodsByViewCnt();
        System.out.println(foodDTOs);
        return foodDTOs;
    }

    @Override
    public FoodDTO foodViewF(Long foodNum) {
        return calmapper.getViewF(foodNum);
    }

    ;

    @Override
    public void updateViewCountF(Long foodNum) {
        calmapper.updateViewCountF(foodNum);
    }

    @Override
    public List<FoodDTO> getFindFood(String keyword) {

        return calmapper.FindFoodNameModal(keyword);
    }

    @Override
    public List<FoodDTO> getFood(String foodNumber) {
        return calmapper.findByFoodNum(foodNumber);
    }

    @Override
    public List<ExerciseDTO> getFindExec(String keyword) {
        return calmapper.FindExecNameModal(keyword);
    }

    public List<ExerciseDTO> searchExecs(String keyword) {
        System.out.println(keyword);
        List<ExerciseDTO> databaseResults = calmapper.findByExecName(keyword);
        System.out.println(databaseResults);
        if (databaseResults == null || databaseResults.isEmpty()) {
            return new ArrayList<>();
        }

        // 각 항목의 연관성 점수를 계산하고 설정
        for (ExerciseDTO exec : databaseResults) {
            double relevanceScore = calculateRelevanceScore2(exec, keyword);
            exec.setRelevanceScore(relevanceScore);
        }

        // 연관성 점수에 따라 내림차순으로 정렬
        databaseResults.sort((exec1, exec2) ->
                Double.compare(exec2.getRelevanceScore(), exec1.getRelevanceScore())
        );

        // 상위 30개 항목 선택
        int limit = Math.min(databaseResults.size(), 30);
        return databaseResults.subList(0, limit);
    }

    private double calculateRelevanceScore2(ExerciseDTO exec, String keyword) {
        String execName = exec.getExecName().toLowerCase(); // 검색 결과의 식품 이름
        keyword = keyword.toLowerCase(); // 입력 키워드

        // 키워드와 식품 이름 사이의 연관성을 계산하는 간단한 방법
        // 예를 들어, 키워드가 식품 이름에 포함되면 연관성이 높다고 가정
        if (execName.contains(keyword)) {
            return 1.0; // 연관성이 높음
        } else {
            return 0.0; // 연관성이 낮음
        }
    }

    @Override
    public List<ExerciseDTO> getTop30execs() {
        List<ExerciseDTO> execDTOs = calmapper.getTop30ExecsByViewCnt();
        System.out.println(execDTOs);
        return execDTOs;
    }

    @Override
    public ExerciseDTO execViewE(Long exerNum) {
        ExerciseDTO viewE = calmapper.getViewE(exerNum);
        return viewE;
    };
    @Override
    public void updateViewCountE(Long execNum) {
        calmapper.updateViewCountE(execNum);
    }
}