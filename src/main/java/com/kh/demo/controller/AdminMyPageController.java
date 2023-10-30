package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.domain.dto.ReportDTO;
import com.kh.demo.service.ReportService;
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

    @GetMapping("adminmypage_list")
    public void replaceList(){}

    @GetMapping("adminmypage_report")
    public void reportList(Criteria cri, Model model) throws Exception{
        System.out.println(cri);
        List<ReportDTO> reportList = reportService.getReportList(cri);
        model.addAttribute("reportList" , reportList);
        model.addAttribute("pageMaker",new PageDTO(reportService.getTotal(cri), cri));

        System.out.println(cri.getPagenum());
    }

    @GetMapping("allReport")
    @ResponseBody
    public List<ReportDTO> allReportList(Criteria cri, Model model) throws Exception {
        List<ReportDTO> reportList = reportService.getReportList(cri);
        return reportList;
    }

    @GetMapping("reportByUser")
    @ResponseBody
    public List<ReportDTO> reportListByUser(Criteria cri, Model model) throws Exception {
        List<ReportDTO> reportListByUser = reportService.getReportListByUser(cri);
        return reportListByUser;
    }

    @GetMapping("reportByTrainer")
    @ResponseBody
    public List<ReportDTO> reportListByTrainer(Criteria cri, Model model) throws Exception {
        List<ReportDTO> reportListByTrainer = reportService.getReportListByTrainer(cri);
        return reportListByTrainer;
    }

    @GetMapping("adminmypage_trainer")
    public void replaceTrainer(){}

    @GetMapping("adminmypage_board")
    public void replaceBoard(){}

    @GetMapping("adminmypage_usersearch")
    public void replaceUserSearch(){}

    @GetMapping("adminmypage_message")
    public void replaceMessage(){}

}
