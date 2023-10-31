package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info/*")
public class InfoController {

    @GetMapping("info_list")
    public void replaceInfo(){
    }

    @GetMapping("info_news")
    public void replaceInfo_news(){
    }

    @GetMapping("info_exercise")
    public void replaceInfo_exercise(){
    }

    @GetMapping("info_food")
    public void replaceInfo_food(){
    }

    @GetMapping("info_tip")
    public void replaceInfo_tip(){
    }

}
