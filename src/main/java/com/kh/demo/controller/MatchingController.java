package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matching/*")
public class MatchingController {

    @GetMapping("list")
    public void replace(){}

}
