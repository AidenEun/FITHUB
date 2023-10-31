package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.ReportService;
import com.kh.demo.service.TrainerSignUpService;
import com.kh.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/adminmypage/*")
public class AdminMyPageController {
    @Autowired @Qualifier("ReportServiceImpl")
    private ReportService reportService;
    @Autowired @Qualifier("TrainerSignUpServiceImpl")
    private TrainerSignUpService signUpService;
    @Autowired @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;

    @GetMapping("adminmypage_list")
    public void replaceList(){}

    @GetMapping("adminmypage_report")
    public void reportList(Criteria cri, Model model) throws Exception{
        List<ReportDTO> reportList = reportService.getReportList(cri);

        model.addAttribute("reportList" , reportList);
        model.addAttribute("pageMaker",new PageDTO(reportService.getTotal(cri), cri));
    }

    @GetMapping("allReport")
    @ResponseBody
    public String allReportList(Criteria cri, Model model) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        List<ReportDTO> reportList = reportService.getReportList(cri);
        PageDTO pageDTO = new PageDTO(reportService.getTotal(cri), cri);

        json.putPOJO("reportList", reportList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("reportByUser")
    @ResponseBody
    public String reportListByUser(Criteria cri, Model model) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        List<ReportDTO> reportListByUser = reportService.getReportListByUser(cri);
        PageDTO pageDTO = new PageDTO(reportService.getTotalByUser(cri), cri);

        json.putPOJO("reportList", reportListByUser);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("reportByTrainer")
    @ResponseBody
    public String reportListByTrainer(Criteria cri, Model model) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        List<ReportDTO> reportListByTrainer = reportService.getReportListByTrainer(cri);
        PageDTO pageDTO = new PageDTO(reportService.getTotalByTrainer(cri), cri);

        json.putPOJO("reportList", reportListByTrainer);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("adminmypage_trainer")
    public void replaceTrainer(Criteria cri, Model model){
        List<TrainerSignUpDTO> trainerSingupDTO = signUpService.getSignUpList(cri);
        List<UserDTO> userDTO = userService.getSignUpListInUser(cri);

        userDTO.forEach(user -> user.setUserBirth(user.getAge())); // 생년월일 필드를 나이로 덮어쓰기

        model.addAttribute("signUpList", trainerSingupDTO);
        model.addAttribute("signUpListInUser", userDTO);
    }

    @GetMapping("adminmypage_board")
    public void replaceBoard(){}

    @GetMapping("adminmypage_usersearch")
    public void replaceUserSearch(){}

    @GetMapping("adminmypage_message")
    public void replaceMessage(){}

}
