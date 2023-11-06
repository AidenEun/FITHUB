package com.kh.demo.controller;

import com.kh.demo.domain.dto.ReportDTO;
import com.kh.demo.domain.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {

    @PostMapping("/report")
    public ResponseEntity<String> report(@RequestBody ReportDTO reportData, HttpSession session) {
        String reportContent = reportData.getReportContent();
        Long boardNum = reportData.getReportBoardnum();
        String boardCategory = reportData.getBoardCategory();
        Object userId = session.getAttribute("loginUser");

        System.out.println(userId);
        System.out.println(boardCategory);
        System.out.println(boardNum);
        System.out.println(reportContent);

        return ResponseEntity.ok("신고가 처리되었습니다.");
    }

}
