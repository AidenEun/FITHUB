package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminmypage/*")
public class AdminMyPageController {

    @GetMapping("adminmypage_list")
    public void replaceList(){}

    @GetMapping("adminmypage_message")
    public void replaceMessage(){}

    @GetMapping("adminmypage_usersearch")
    public void replaceUserSearch(){}

    @GetMapping("adminmypage_board")
    public void replaceBoard(){}

    @GetMapping("adminmypage_trainer")
    public void replaceTrainer(){}

    @GetMapping("adminmypage_report")
    public void replaceReport(){}

}
