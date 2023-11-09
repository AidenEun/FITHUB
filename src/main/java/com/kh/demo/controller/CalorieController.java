package com.kh.demo.controller;

import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.ExerciseDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/calorie/*")
public class CalorieController {


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
        FoodDTO food = calorieService.foodViewF(foodNum);

        if (food == null) {
            return "error";
        }

        HttpSession session = req.getSession();
        model.addAttribute("food", food);

        // foodNum에 대한 조회수 증가 처리
        Cookie[] cookies = req.getCookies();
        boolean hasViewed = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("view_food" + foodNum)) {
                    hasViewed = true;
                    break;
                }
            }
        }

        if (!hasViewed) {
            // 조회수 증가
            calorieService.updateViewCountF(foodNum);

            // "view_food{foodNum}" 이름의 쿠키(유효기간: 3600초)를 생성해서 클라이언트 컴퓨터에 저장
            Cookie cookie = new Cookie("view_food" + foodNum, "viewed");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
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

    @PostMapping("foodModal_search")
    @ResponseBody
    public String foodModal_search(@RequestParam("keyword") String keyword) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
//        System.out.println(keyword);

        List<FoodDTO> foodList = calorieService.getFindFood(keyword);
//        System.out.println(foodList);
        json.putPOJO("foodList", foodList);

        return json.toString();
    }

    @PostMapping("execModal_search")
    @ResponseBody
    public String execModal_search(@RequestParam("keyword") String keyword) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        List<ExerciseDTO> execList = calorieService.getFindExec(keyword);

        json.putPOJO("execList", execList);

        return json.toString();
    }

    @PostMapping("getfoodInfo")
    @ResponseBody
    public String getfoodInfo(@RequestParam("todayAllList") String todayAllList) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        String[] todayListArr = todayAllList.split(",");

        double allCarbo = 0.0;
        double allProtein = 0.0;
        double allFat = 0.0;
        String foodNumber = "";

        for (int i = 0; i < todayListArr.length; i++) {
            foodNumber = todayListArr[i];
            List<FoodDTO> todayFoodDTOList = calorieService.getFood(foodNumber);
            for (int j = 0; j < todayFoodDTOList.size(); j++) {

                allCarbo += Double.parseDouble(todayFoodDTOList.get(j).getCarbo());
                allProtein += Double.parseDouble(todayFoodDTOList.get(j).getProtein());
                allFat += Double.parseDouble(todayFoodDTOList.get(j).getFat());
            }

        }
        double resultCarbo = Math.round(allCarbo * 100.0) / 100.0;
        double resultProtein = Math.round(allProtein * 100.0) / 100.0;
        double resultFat = Math.round(allFat * 100.0) / 100.0;

        double[] result = {resultCarbo, resultProtein, resultFat};

        json.putPOJO("todayfoodInfo", result);

        return json.toString();
    }

    @GetMapping("exercise_calorie_search")
    public String showExecCalories(@RequestParam("keyword") String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            List<ExerciseDTO> execs = calorieService.searchExecs(keyword);
            model.addAttribute("execs", execs);
            model.addAttribute("searchKeyword", keyword);
            // 검색 결과 개수 계산
            int searchCount = execs.size();
            model.addAttribute("searchCount", searchCount);

            if (searchCount == 0) {
                return "calorie/exercise_calorie_search";
            }
        } else {
            // 사용자가 검색어를 입력하지 않았을 때, 에러 메시지를 표시
            model.addAttribute("noResultsMessage", "검색어를 입력해 주세요.");
        }
        return "calorie/exercise_calorie_search";
    }


    @GetMapping("/calorie/exercise_calorie_view")
    public String get2(@RequestParam Long execNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        ExerciseDTO exec = calorieService.execViewE(execNum);

        if (exec == null) {
            return "error";
        }

        HttpSession session = req.getSession();
        model.addAttribute("exec", exec);

        // foodNum에 대한 조회수 증가 처리
        Cookie[] cookies = req.getCookies();
        boolean hasViewed = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("view_food" + execNum)) {
                    hasViewed = true;
                    break;
                }
            }
        }

        if (!hasViewed) {
            // 조회수 증가
            calorieService.updateViewCountF(execNum);

            // "view_food{foodNum}" 이름의 쿠키(유효기간: 3600초)를 생성해서 클라이언트 컴퓨터에 저장
            Cookie cookie = new Cookie("view_exec" + execNum, "viewed");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
        }

        return "calorie/exercise_calorie_view";
    }


    @GetMapping("exercise_calorie_list")
    public void exercise_calorie_list(Model model) throws Exception {
        List<ExerciseDTO> topExers = calorieService.getTop30execs();
        if (topExers != null && topExers.size() > 0) {
            model.addAttribute("list", topExers);
        }
        System.out.println(topExers);
    }
}


