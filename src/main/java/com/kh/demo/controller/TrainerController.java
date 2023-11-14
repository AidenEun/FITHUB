package com.kh.demo.controller;

import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.service.TrainerMyPageService;
import com.kh.demo.service.TrainerService;
import com.kh.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trainer/*")
public class TrainerController {

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService service;


    @GetMapping("logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/";
    }

}
