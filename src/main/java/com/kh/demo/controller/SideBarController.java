package com.kh.demo.controller;


import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.service.UserMyPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/sidebar/*")
public class SideBarController {

    private final UserMyPageService userMyPageService;

    @Autowired
    public SideBarController(UserMyPageService userMyPageService) {
        this.userMyPageService = userMyPageService;
    }

    @GetMapping("userMyInfo")
    public String userMyInfo(Model model, Principal principal) {
        String loginUser = principal.getName();
        UserDTO user = userMyPageService.getUserDetail(loginUser);
        model.addAttribute("user", user);
        // 이후 필요한 다른 데이터도 가져와서 모델에 추가할 수 있습니다.
        // 예: List<DiaryDTO> diaryList = userMyPageService.getDiaryList(loginUser);
        // model.addAttribute("diaryList", diaryList);
        return "sidebar/userMyInfo";

    }


    @GetMapping("#")
    public void replace(){}


    @GetMapping("bmi")
    public void BMI(){}

    @GetMapping("healthCenter")
    public void healthCenter(){}

}
