package com.kh.demo.controller;

import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.TrainerMapper;
import com.kh.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService service;

    @Autowired
    @Qualifier("UserMyPageServiceImpl")
    private UserMyPageService umpservice;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService serviceTrainer;

    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService serviceAdmin;

    @Autowired @Qualifier("BoardServiceImpl")
    private BoardService boardservice;

    @Autowired
    @Qualifier("TrainerMatchingServiceImpl")
    private TrainerMatchingService MatchingService;

    @Autowired
    private ChallengeService challService;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService tservice;

    @RequestMapping("/")
    public String home(HttpServletRequest req, Model model){

        BoardDTO newsBoard = boardservice.getNewsTop1();
        BoardDTO exerBoard = boardservice.getExerTop1();
        BoardDTO foodBoard = boardservice.getFoodTop1();
        model.addAttribute("newsTop1", newsBoard);
        model.addAttribute("exerTop1", exerBoard);
        model.addAttribute("foodTop1", foodBoard);

        List<TrainerDTO> trainerTopList = serviceTrainer.getTrainerTopList();
        model.addAttribute("trainerTopList", trainerTopList);
        return "index";
    }

//    @GetMapping("/index_sidebar")
//    public void replace(HttpServletRequest req, Model model) {
//        HttpSession session = req.getSession();
//        String userid = (String) session.getAttribute("loginUser");
//        String loginUser = (String) session.getAttribute("loginUser");
//        UserDTO user = umpservice.getUserDetail(loginUser);
//
////       오늘 날짜 구하기
//        LocalDate todaydate = LocalDate.now();
//
//        model.addAttribute("user", user);
//        model.addAttribute("todaydate", todaydate);
//
//    }


}
