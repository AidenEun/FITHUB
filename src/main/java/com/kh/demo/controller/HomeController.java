package com.kh.demo.controller;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.TrainerSignUpDTO;
import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService service;

    @RequestMapping("/")
    public String home(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();
        String loginUser= (String)session.getAttribute("loginUser");
        UserDTO user = service.getDetail(loginUser);

        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/index_sidebar")
    public void replace(){}

    @GetMapping("/user/agree")
    public void agree(){}



}
