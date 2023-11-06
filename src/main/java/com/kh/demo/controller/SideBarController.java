package com.kh.demo.controller;

import com.kh.demo.domain.dto.TrainerDTO;
import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sidebar/*")
public class SideBarController {

    @Autowired
    private UserService userService;

    //나중에 필요시 수정예정
//    @Autowired
//    private TrainerService trainerService;

    //사용자 정보를 가져오는 로직
    @GetMapping("user_myinfo")
    public String sidebarInfo(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("loginUser");
        UserDTO user = userService.getDetail(userId);
        model.addAttribute("user", user);
        return "index";
    }

    //나중에 필요시 수정예정

//    @GetMapping("trainer_info")
//    public String sidebarTrainerInfo(Model model, HttpSession session) {
//        String userId = (String) session.getAttribute("loginUser");
//        TrainerDTO trainer = trainerService.getTrainerInfo(userId);
//        model.addAttribute("trainer", trainer);
//        return "index";
//    }
    @GetMapping("#")
    public void replace(){}

    @GetMapping("bmi")
    public void BMI(){}

    @GetMapping("healthCenter")
    public void healthCenter(){}

}
