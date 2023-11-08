package com.kh.demo.controller;

import com.kh.demo.domain.dto.*;
import com.kh.demo.service.AdminService;
import com.kh.demo.service.TrainerService;
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

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService serviceTrainer;

    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService serviceAdmin;

    @RequestMapping("/")
    public String home(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();
        String loginUser= (String)session.getAttribute("loginUser");

        UserDTO user = service.getDetail(loginUser);
        TrainerDTO trainer = serviceTrainer.getDetail(loginUser);
        AdminDTO admin = serviceAdmin.getDetail(loginUser);


        if(admin != null){
            req.getSession().setAttribute("admin",admin);
            return "index";
        } else if (trainer != null) {
            req.getSession().setAttribute("trainer",trainer);
            return "index";
        } else if(user!=null){
            req.getSession().setAttribute("user",user);
            return "index";
        }
        System.out.println("user:"+user);
        System.out.println("trainer:"+trainer);
        System.out.println("admin:"+admin);
        return "index";
    }

    @GetMapping("/index_sidebar")
    public void replace(){}

    @GetMapping("/user/agree")
    public void agree(){}



}
