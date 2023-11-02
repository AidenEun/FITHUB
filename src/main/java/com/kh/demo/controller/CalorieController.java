package com.kh.demo.controller;

import com.kh.demo.domain.dto.FoodDTO;
import com.kh.demo.service.CalorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/calorie/*")
public class CalorieController {

    @GetMapping("exercise_calorie_list")
    public void exercise_calorie_list(){}

    @Autowired
    private CalorieService calorieService;

    @GetMapping("food_calorie_list")
    public String food_calorie_list() {
        return "calorie/food_calorie_list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            List<FoodDTO> foods = calorieService.searchFoods(keyword);
            model.addAttribute("foods", foods);
            model.addAttribute("searchKeyword", keyword);

            // 검색 결과 개수 계산
            int searchCount = foods.size();
            model.addAttribute("searchCount", searchCount);
        } else {
            // 사용자가 검색어를 입력하지 않았을 때의 처리 (예: 에러 메시지 표시)
        }
        return "calorie/food_calorie_search";
    }

    @GetMapping("/getSuggestedFoods")
    @ResponseBody
    public List<String> getSuggestedFoods(@RequestParam("keyword") String keyword) {
        // 키워드를 기반으로 음식 항목을 검색하는 로직을 구현
        List<String> suggestedFoods = calorieService.getSuggestedFoods(keyword);
        return suggestedFoods;
    }

}
