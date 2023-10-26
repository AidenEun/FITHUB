package com.kh.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

    @GetMapping("logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/";
    }

}
