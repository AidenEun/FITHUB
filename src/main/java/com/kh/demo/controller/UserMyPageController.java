package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/usermypage/*")
public class UserMyPageController {

    @Autowired @Qualifier("UserMyPageServiceImpl")
    private UserMyPageService service;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService serviceUser;



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
    public void replaceChallenge( String challCategory ,String challTerm ,Criteria cri, Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        System.out.println("challCategory : "+challCategory);
        System.out.println("challTerm : "+challTerm);

        if(challCategory == null){
            challCategory = "challAll";
        }
        if(challTerm == null){
            challTerm = "challengeAll";
        }

        List<ChallNoticeBoardDTO> list = service.getMyChallenge(cri, userId,challCategory,challTerm);
        System.out.println("list:"+list);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker", new PageDTO(service.getChallengeTotal(cri,userId, challCategory,challTerm), cri));
    }




    @GetMapping("user_subtrainer")
    public void replaceSubTrainer(Criteria cri, Model model, HttpServletRequest req){
        cri = new Criteria(cri.getPagenum(), 12);
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
        model.addAttribute("pageMaker", new PageDTO(service.getBookmarkTotal(cri,userId), cri));
    }

    @GetMapping("board_info")
    @ResponseBody
    public String board_info(@RequestParam("pageNum") int pageNum, HttpServletRequest req) throws Exception{
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<BoardDTO> list = service.getMyBookmark(cri,userId);
        PageDTO pageDTO = new PageDTO(service.getBookmarkTotal(cri,userId), cri);
        json.putPOJO("list", list);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("board_product")
    @ResponseBody
    public String board_product(@RequestParam("pageNum") int pageNum, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");



        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<ProductBoardDTO> list = service.getMyBookmarkProduct(cri,userId);
        PageDTO pageDTO = new PageDTO(service.getBookmarkProductTotal(cri,userId), cri);
        json.putPOJO("list", list);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("user_boardlist")
    public void user_boardlist(Criteria cri, Model model,HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        List<BoardDTO> list = service.getBoardMyList(cri,userId);
      /* List<BoardDTO> list = serviceBoard.getBoardList(cri);*/
        System.out.println("cri : "+cri);
        System.out.println("PageDTO : "+new PageDTO(service.getBoardTotal(cri, userId), cri));
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getBoardTotal(cri,userId), cri));
        model.addAttribute("newly_board",service.getBoardNewlyList(list));
        model.addAttribute("reply_cnt_list",service.getBoardReplyCntList(list));
        model.addAttribute("recent_reply",service.getBoardRecentReplyList(list));
    }

    @GetMapping("user_messagelist")
    public void user_messagelist(String message ,Criteria cri,Model model,HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        if(message == null){
            message = "messageAll";
        }
        List<MessageDTO> list = service.getMessageMyList(cri,userId,message);

        System.out.println(cri);
        System.out.println("list:"+list);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getMessageTotal(cri,userId,message), cri));
        model.addAttribute("newly_Message",service.getMessageNewlyList(list));
    }

    @GetMapping("user_applytrainer")
    public void user_applytrainer(HttpServletRequest req,Model model) {
        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        UserDTO user = service.getUserDetail(loginUser);
        model.addAttribute("user", user);
    }

    @PostMapping("applytrainer")
    public String applytrainer(TrainerSignUpDTO user, MultipartFile[] files) throws Exception{
        if(service.insertApplytrainer(user,files)){
            return "redirect:/usermypage/user_applytrainer";
        }
        return "redirect:/";
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
