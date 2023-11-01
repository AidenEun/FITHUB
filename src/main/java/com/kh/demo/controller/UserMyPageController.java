package com.kh.demo.controller;

import com.kh.demo.domain.dto.*;
import com.kh.demo.service.BoardService;
import com.kh.demo.service.MessageService;
import com.kh.demo.service.UserMyPageService;
import com.kh.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usermypage/*")
public class UserMyPageController {

    @Autowired @Qualifier("UserMyPageServiceImpl")
    private UserMyPageService service;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService serviceUser;

    @Autowired
    @Qualifier("BoardServiceImpl")
    private BoardService serviceboard;

    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService serviceMessage;


    @GetMapping("user_myinfo_modify")
    public void user_myinfo_modify(HttpServletRequest req,Model model){
        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        UserDTO user = serviceUser.getDetail(loginUser);
        model.addAttribute("user", user);
    }

    @GetMapping("user_myinfo")
    public void user_myinfo(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        UserDTO user = serviceUser.getDetail(loginUser);
        model.addAttribute("user", user);
    }

    @PostMapping("user_myinfo_modify")
    public String user_myinfo_modify(UserDTO userdto, Model model) {
        System.out.println(userdto);
        if (serviceUser.user_modify(userdto)){
            UserDTO user = serviceUser.getDetail(userdto.getUserId());
            model.addAttribute("user", user);
            return "redirect:/usermypage/user_myinfo";
        }
        else {
            return "redirect:/";
        }
    }

    @GetMapping("user_challenge")
    public void replaceChallenge(){}

    @GetMapping("user_subtrainer")
    public void replaceSubTrainer(){}

    @GetMapping("user_subbookmark")
    public void replaceSubBookmark(){}

    @GetMapping("user_boardlist")
    public void user_boardlist(Criteria cri, Model model,HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        List<BoardDTO> list = serviceboard.getMyBoardList(cri,userId);
      /* List<BoardDTO> list = serviceboard.getBoardList(cri);*/
        System.out.println("cri : "+cri);
        System.out.println("list : "+list);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(serviceboard.getTotal(cri), cri));
        model.addAttribute("newly_board",serviceboard.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",serviceboard.getReplyCntList(list));
        model.addAttribute("recent_reply",serviceboard.getRecentReplyList(list));
    }

    @GetMapping("user_messagelist")
    public void user_messagelist(Criteria cri, Model model,HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        List<MessageDTO> list = serviceMessage.getMyMessageList(cri,userId);
        System.out.println(cri);
        System.out.println("list:"+list);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(serviceMessage.getTotal(cri), cri));
        model.addAttribute("newly_Message",serviceMessage.getNewlyMessageList(list));
    }

    @GetMapping("user_applytrainer")
    public void replaceApplyTrainer(){}

    @GetMapping("user_diary")
    public void replaceDiary(String loginUser, Model model) {
        List<DiaryDTO> diaryList = service.getDiaryList(loginUser);
        model.addAttribute("diaryList",diaryList);
    }

    //userid도 같이 넘어와야함
    @GetMapping("checklist")
    public String checklist(String choicedate, RedirectAttributes ra){
        //작성으로 이동
        if(service.checkList(choicedate) == null){
            System.out.println(choicedate);
            ra.addAttribute("regdate",choicedate);
            return "redirect:/usermypage/diaryWrite";
        }
        //view로 이동
        else {
            return "redirect:/usermypage/diaryView";
        }
    }

    @GetMapping("diaryWrite")
    public void replacediaryWrite(){

    }
    @GetMapping ("diaryView")
    public void replacediaryView() {}

}
