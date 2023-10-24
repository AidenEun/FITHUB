package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/*")
public class TestController {

    @GetMapping("info")
    public void replaceInfo(){}

    @GetMapping("cal")
    public void replaceCal(){}
}
