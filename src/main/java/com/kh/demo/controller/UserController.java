package com.kh.demo.controller;

import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService service;

    @GetMapping("join")
    public void replace() {}

    @PostMapping("join")
    public String join(@ModelAttribute("user") UserDTO user, RedirectAttributes ra) {
        if(service.join(user)) {
            ra.addAttribute("joinid",user.getUserId());
        }
        return "redirect:/";
    }
    @GetMapping("login")
    public void replaceLogin(){}

    @PostMapping("login")
    public String login(String userid, String userpw, HttpServletRequest req) {
        UserDTO loginUser = service.login(userid, userpw);
        if(loginUser != null) {
            req.getSession().setAttribute("loginUser", loginUser.getUserId());
            UserDTO user = service.getDetail(loginUser.getUserId());
            req.getSession().setAttribute("user", user);
            return "redirect:/";
        }
        else {
            return "redirect:/";
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("checkid")
    @ResponseBody
    public String checkId(String userid) {
        if(service.checkId(userid)) {
            return "O";
        }
        return "X";
    }
}
