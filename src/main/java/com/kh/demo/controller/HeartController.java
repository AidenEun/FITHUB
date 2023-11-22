package com.kh.demo.controller;

import com.kh.demo.domain.dto.LikeDTO;
import com.kh.demo.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/heart/*")
public class HeartController {


    @Autowired @Qualifier("HeartServiceImpl")
    private HeartService service;

    @PostMapping("heart")
    @ResponseBody
    public int heart(@ModelAttribute LikeDTO heart){
        int result = service.insertHeart(heart);
        System.out.println(result);
        return result;
    }

}
