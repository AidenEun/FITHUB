package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usermypage/*")
public class UserMyPageController {

    @GetMapping("list")
    public void replaceInfo(){}

}
