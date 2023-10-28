package com.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trainermypage/*")
public class TrainerMyPageController {

    @GetMapping("trainer_modify")
    public void replaceModify(){}

    @GetMapping("trainer_challenge")
    public void replaceChallenge(){}
}
