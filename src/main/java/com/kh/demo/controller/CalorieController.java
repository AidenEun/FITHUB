package com.kh.demo.controller;

import com.fasterxml.jackson.databind.DatabindContext;
import com.kh.demo.domain.dto.FoodDTO;
import com.kh.demo.service.CalorieService;
import com.kh.demo.service.CalorieServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/calorie/*")
public class CalorieController {

    @GetMapping("exercise_calorie_list")
    public void exercise_calorie_list(){}


    @Autowired
    private CalorieService calorieService;


    @GetMapping("food_calorie_search")
    public String showFoodCalories(@RequestParam("keyword") String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            List<FoodDTO> foods = calorieService.searchFoods(keyword);
            model.addAttribute("foods", foods);
            model.addAttribute("searchKeyword", keyword);

            // 검색 결과 개수 계산
            int searchCount = foods.size();
            model.addAttribute("searchCount", searchCount);

            if (searchCount == 0) {
                return "calorie/food_calorie_search";
            }
        } else {
            // 사용자가 검색어를 입력하지 않았을 때, 에러 메시지를 표시
            model.addAttribute("noResultsMessage", "검색어를 입력해 주세요.");
        }
        return "calorie/food_calorie_search";
    }
    @GetMapping("/calorie/food_calorie_view")
    public String get(@RequestParam Long foodNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        FoodDTO food = calorieService.foodView(foodNum);

        if (food == null) {
            return "error";
        }
        HttpSession session = req.getSession();
        model.addAttribute("food", food);
        System.out.println(food);
        // foodNum에 대한 조회수 증가 처리
        Cookie[] cookies = req.getCookies();
        Cookie viewFood = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("view_food" + foodNum)) {
                    viewFood = cookie;
                    break;
                }
            }
        }
        if (viewFood == null) {
            // 조회수 증가
            calorieService.updateViewCount(foodNum);
            // "view_food{foodNum}" 이름의 쿠키(유효기간: 3600초)를 생성해서 클라이언트 컴퓨터에 저장
            Cookie cookie = new Cookie("view_food" + foodNum, "r");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
            System.out.println("cookie" + cookie);
        }

        return "calorie/food_calorie_view";
    }


    @GetMapping("food_calorie_list")
    public void food_calorie_list(Model model) throws Exception {
        List<FoodDTO> topFoods = calorieService.getTop30Foods();
        if (topFoods != null && topFoods.size() > 0) {
            model.addAttribute("list", topFoods);
        }
        System.out.println(topFoods);
    }
}


