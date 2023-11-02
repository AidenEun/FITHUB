package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calorie/*")
public class CalorieController {

    @GetMapping("food_calorie_list")
    public void food_calorie_list(){}

    @GetMapping("exercise_calorie_list")
    public void exercise_calorie_list(){}

}
