package com.kh.demo.controller;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.service.BoardService;
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

    @Autowired
    @Qualifier("boardServiceImpl")
    private BoardService service;

    @GetMapping("user_modify")
    public void replaceModify(){}


    @GetMapping("user_challenge")
    public void replaceChallenge(){}

    @GetMapping("user_subtrainer")
    public void replaceSubTrainer(){}

    @GetMapping("user_subbookmark")
    public void replaceSubBookmark(){}

    @GetMapping("user_boardlist")
    public void list(Criteria cri, Model model) throws Exception {
        System.out.println(cri);
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }

    @GetMapping("user_message")
    public void replaceMessage(){}

    @GetMapping("user_applytrainer")
    public void replaceApplyTrainer(){}


}
