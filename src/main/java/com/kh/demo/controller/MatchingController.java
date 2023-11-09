package com.kh.demo.controller;

import com.kh.demo.domain.dto.*;
import com.kh.demo.service.TrainerMatchingService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/matching/*")
public class MatchingController {

    @Autowired
    @Qualifier("TrainerMatchingServiceImpl")
    private TrainerMatchingService MatchingService;

    @GetMapping("matching_list")
    public void matching_list(Criteria cri, Model model) throws Exception {
        List<TrainerMatchingBoardDTO> list = MatchingService.getmatchingList(cri);
        model.addAttribute("list", list);

       /* model.addAttribute("review_cnt_list",MatchingService.getReviewCntList(list));*/
    }

    @GetMapping("thumbnail")
    public ResponseEntity<Resource> thumbnail(String sysName) throws Exception{
        return MatchingService.getThumbnailResource(sysName);
    }

    @GetMapping("matching_write")
    public void write(@ModelAttribute("cri") Criteria cri,Model model) {
        System.out.println(cri);
    }

    @PostMapping("matching_write")
    public String write(TrainerMatchingBoardDTO board, Criteria cri) throws Exception{
        Long boardnum = 0l;
        if(MatchingService.regist(board)) {
            boardnum = MatchingService.getLastNum(board.getTrainerId());
            return "redirect:/matching/matching_view"+cri.getListLink()+"&boardnum="+boardnum;
        }
        else {
            return "redirect:/matching/matching_list"+cri.getListLink();
        }
    }



    @GetMapping("/matching/matching_view")
    public String get(@RequestParam Long boardNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        List<TrainerMatchingBoardDTO> list = MatchingService.boardView(boardNum);



        if (list == null) {
            return "error";
        }

        HttpSession session = req.getSession();
        model.addAttribute("list", list);

        // foodNum에 대한 조회수 증가 처리
        Cookie[] cookies = req.getCookies();
        boolean hasViewed = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("view_board" + boardNum)) {
                    hasViewed = true;
                    break;
                }
            }
        }

        if (!hasViewed) {
            // 조회수 증가
            MatchingService.updateViewCount(boardNum);

            // "view_food{foodNum}" 이름의 쿠키(유효기간: 3600초)를 생성해서 클라이언트 컴퓨터에 저장
            Cookie cookie = new Cookie("view_food" + boardNum, "viewed");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
        }

        return "matching/matching_view";
    }



}
