package com.kh.demo.controller;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.DiaryDTO;
import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.service.BoardService;
import com.kh.demo.service.UserMyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usermypage/*")
public class UserMyPageController {

    @Autowired @Qualifier("UserMyPageServiceImpl")
    private UserMyPageService service;

    @Autowired
    @Qualifier("BoardServiceImpl")
    private BoardService serviceboard;


    @GetMapping("user_modify")
    public void replaceModify(){}


    @GetMapping("user_challenge")
    public void replaceChallenge(){}

    @GetMapping("user_subtrainer")
    public void replaceSubTrainer(){}

    @GetMapping("user_subbookmark")
    public void replaceSubBookmark(){}

    @GetMapping("user_boardlist")
    public void user_boardlist(Criteria cri, Model model) throws Exception {
        System.out.println(cri);
        List<BoardDTO> list = serviceboard.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(serviceboard.getTotal(cri), cri));
        model.addAttribute("newly_board",serviceboard.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",serviceboard.getReplyCntList(list));
        model.addAttribute("recent_reply",serviceboard.getRecentReplyList(list));
    }

    @GetMapping("user_messagelist")
    public void user_messagelist(Criteria cri, Model model) throws Exception {
        System.out.println(cri);
        List<BoardDTO> list = serviceboard.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(serviceboard.getTotal(cri), cri));
        model.addAttribute("newly_board",serviceboard.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",serviceboard.getReplyCntList(list));
        model.addAttribute("recent_reply",serviceboard.getRecentReplyList(list));
    }

    @GetMapping("user_applytrainer")
    public void replaceApplyTrainer(){}

    @GetMapping("user_diary")
    public void replaceDiary(String loginUser, Model model) {
        List<DiaryDTO> diaryList = service.getDiaryList(loginUser);
        model.addAttribute("diaryList",diaryList);
    }

}
