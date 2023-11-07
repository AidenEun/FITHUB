package com.kh.demo.controller;

import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.domain.dto.TrainerMatchingBoardDTO;
import com.kh.demo.service.TrainerMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kh.demo.domain.dto.Criteria;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/matching/*")
public class MatchingController {

    @Autowired
    @Qualifier("TrainerMatchingServiceImpl")
    private TrainerMatchingService MatchingService;

    @GetMapping("matching_list")
    public void matching_list(Criteria cri, Model model) throws Exception {
        List<TrainerMatchingBoardDTO> list = MatchingService.getmatchingList();
        model.addAttribute("list", list);
        model.addAttribute("pageMaker",new PageDTO(MatchingService.getTotal(cri), cri));
        model.addAttribute("newly_board",MatchingService.getNewlyBoardList(list));
        model.addAttribute("recent_reply",MatchingService.getRecentReplyList(list));
    }

    @GetMapping("matching_write")
    public void write(Criteria cri, Model model) throws Exception {
        List<TrainerMatchingBoardDTO> list = MatchingService.getmatchingList();
        model.addAttribute("list", list);
        model.addAttribute("pageMaker",new PageDTO(MatchingService.getTotal(cri), cri));
        model.addAttribute("newly_board",MatchingService.getNewlyBoardList(list));
        model.addAttribute("recent_reply",MatchingService.getRecentReplyList(list));
    }
    @GetMapping("matching_view")
    public void view(Criteria cri, Model model) throws Exception {
        List<TrainerMatchingBoardDTO> list = MatchingService.getmatchingList();
        model.addAttribute("list", list);
        model.addAttribute("pageMaker",new PageDTO(MatchingService.getTotal(cri), cri));
        model.addAttribute("newly_board",MatchingService.getNewlyBoardList(list));
        model.addAttribute("recent_reply",MatchingService.getRecentReplyList(list));
    }


}
