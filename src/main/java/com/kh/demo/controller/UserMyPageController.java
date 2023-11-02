package com.kh.demo.controller;

import com.kh.demo.domain.dto.*;
import com.kh.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
    private BoardService serviceBoard;

    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService serviceMessage;

    @Autowired
    @Qualifier("BookMarkServiceImpl")
    private BookMarkService serviceBookMark;


    @GetMapping("user_myinfo_modify")
    public void user_myinfo_modify(HttpServletRequest req,Model model){
        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        UserDTO user = service.getUserDetail(loginUser);
        model.addAttribute("user", user);
    }

    @GetMapping("user_myinfo")
    public void user_myinfo(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        UserDTO user = service.getUserDetail(loginUser);
        model.addAttribute("user", user);
    }

    @PostMapping("user_myinfo_modify")
    public String user_myinfo_modify(UserDTO userdto, Model model) {
        System.out.println(userdto);
        if (service.user_modify(userdto)){
            UserDTO user = service.getUserDetail(userdto.getUserId());
            model.addAttribute("user", user);
            return "redirect:/usermypage/user_myinfo";
        }
        else {
            return "redirect:/";
        }
    }

    @GetMapping("user_challenge")
    public void replaceChallenge(Criteria cri, Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        List<ChallNoticeBoardDTO> list = service.getMyChallenge(cri, userId);
        System.out.println("list:"+list);

        model.addAttribute("list",list);
        model.addAttribute("pageMaker", new PageDTO(service.getChallengeTotal(cri,userId), cri));
    }

    @GetMapping("user_subtrainer")
    public void replaceSubTrainer(Criteria cri, Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        List<TrainerDTO> list = service.getMyScribe(cri, userId);
        System.out.println("list:"+list);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker", new PageDTO(service.getScribeTotal(cri,userId), cri));
    }


//북마크 번호 담을려면 보드dto랑 북마크dto 합쳐진 dto 필요
    @GetMapping("user_subbookmark")
    public void user_subbookmark(Criteria cri, Model model, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        List<BoardDTO> list = service.getMyBookmark(cri,userId);
        List<ProductBoardDTO> listProduct = service.getMyBookmarkProduct(cri,userId);
        System.out.println("list:"+list);
        System.out.println("listProduct:"+listProduct);
        model.addAttribute("list",list);
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("pageMaker", new PageDTO(service.getBookmarkTotal(cri,userId), cri));
    }

    @GetMapping("user_boardlist")
    public void user_boardlist(Criteria cri, Model model,HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        List<BoardDTO> list = service.getBoardMyList(cri,userId);
      /* List<BoardDTO> list = serviceBoard.getBoardList(cri);*/
        System.out.println("cri : "+cri);
        System.out.println("PageDTO : "+new PageDTO(service.getBoardTotal(cri), cri));
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getBoardTotal(cri), cri));
        model.addAttribute("newly_board",service.getBoardNewlyList(list));
        model.addAttribute("reply_cnt_list",service.getBoardReplyCntList(list));
        model.addAttribute("recent_reply",service.getBoardRecentReplyList(list));
    }

    @GetMapping("user_messagelist")
    public void user_messagelist(Criteria cri, Model model,HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        List<MessageDTO> list = service.getMessageMyList(cri,userId);
        System.out.println(cri);
        System.out.println("list:"+list);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getMessageTotal(cri), cri));
        model.addAttribute("newly_Message",service.getMessageNewlyList(list));
    }

    @GetMapping("user_applytrainer")
    public void user_applytrainer(HttpServletRequest req,Model model) {
        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        UserDTO user = service.getUserDetail(loginUser);
        model.addAttribute("user", user);
    }

    @GetMapping("user_diary")
    public void replaceDiary(String loginUser, Model model) {
        List<DiaryDTO> diaryList = service.getDiaryList(loginUser);
        model.addAttribute("diaryList",diaryList);
    }

    @GetMapping("checklist")
    public String checklist(String choicedate,HttpServletRequest req, Model model){

        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");

        //작성으로 이동
        if(service.checkList(choicedate,loginUser) == null){
            System.out.println(choicedate);

            UserDTO user = serviceUser.getDetail(loginUser);
            model.addAttribute("regdate",choicedate);
            model.addAttribute("user",user);
            return "/usermypage/diaryWrite";
        }
        //view로 이동
        else {
            UserDTO user = serviceUser.getDetail(loginUser);
            model.addAttribute("user",user);
            return "/usermypage/diaryView";
        }
    }

    @GetMapping("diaryWrite")
    public void replacediaryWrite(){

    }
    @GetMapping ("diaryView")
    public void replacediaryView() {}

}
