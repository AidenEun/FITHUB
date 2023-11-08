package com.kh.demo.controller;

import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.domain.dto.TrainerMatchingBoardDTO;
import com.kh.demo.service.TrainerMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.kh.demo.domain.dto.Criteria;
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
        List<TrainerMatchingBoardDTO> list = MatchingService.getmatchingList();
        model.addAttribute("list", list);
        model.addAttribute("pageMaker",new PageDTO(MatchingService.getTotal(cri), cri));
        model.addAttribute("newly_board",MatchingService.getNewlyBoardList(list));
//        model.addAttribute("reply_cnt_list",MatchingService.getReplyCntList(list));
//        model.addAttribute("recent_reply",MatchingService.getRecentReplyList(list));
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



    @GetMapping("matching_view")
    public void view(Criteria cri, Model model) throws Exception {
        List<TrainerMatchingBoardDTO> list = MatchingService.getmatchingList();
        model.addAttribute("list", list);
        model.addAttribute("pageMaker",new PageDTO(MatchingService.getTotal(cri), cri));
        model.addAttribute("newly_board",MatchingService.getNewlyBoardList(list));
        model.addAttribute("recent_reply",MatchingService.getRecentReplyList(list));
    }


    @GetMapping("/getTrainerInfo")
    @ResponseBody
    public Map<String, String> getTrainerInfo(@RequestParam("trainerId") String trainerId) {
        Map<String, String> trainerInfo = new HashMap<>();
        String nickname = MatchingService.getNickname(trainerId);
                String career = MatchingService.getCareer(trainerId);
                trainerInfo.put("nickname", nickname);
        trainerInfo.put("career", career);
        return trainerInfo;
    }

}
