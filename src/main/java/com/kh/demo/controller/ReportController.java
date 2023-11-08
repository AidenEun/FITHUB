package com.kh.demo.controller;

import com.kh.demo.domain.dto.ReportDTO;
import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.service.ReportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {

    @Autowired @Qualifier("ReportServiceImpl")
    private ReportService reportService;

    @PostMapping("/report")
    public ResponseEntity<String> report(@RequestBody ReportDTO reportData, HttpSession session) {
        String reportContent = reportData.getReportContent();
        String reportedUser = reportData.getReportedUser();
        Long boardNum = reportData.getReportBoardnum();
        String boardCategory = reportData.getBoardCategory();
        Object userId = session.getAttribute("loginUser");

        if(reportService.reportRegist(userId, boardCategory, boardNum, reportContent, reportedUser)){
            return ResponseEntity.ok("신고가 처리되었습니다.");
        }
        return null;
    }

}
