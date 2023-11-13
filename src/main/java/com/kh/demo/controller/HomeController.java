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

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService service;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService serviceTrainer;

    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService serviceAdmin;

    @Autowired @Qualifier("BoardServiceImpl")
    private BoardService boardservice;

    @Autowired
    @Qualifier("TrainerMatchingServiceImpl")
    private TrainerMatchingService MatchingService;

    @Autowired
    private ChallengeService challService;

    @RequestMapping("/")
    public String home(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();
        String loginUser= (String)session.getAttribute("loginUser");

        UserDTO user = service.getDetail(loginUser);
        TrainerDTO trainer = serviceTrainer.getDetail(loginUser);
        AdminDTO admin = serviceAdmin.getDetail(loginUser);

        BoardDTO newsBoard = boardservice.getNewsTop1();
        BoardDTO exerBoard = boardservice.getExerTop1();
        BoardDTO foodBoard = boardservice.getFoodTop1();

        model.addAttribute("newsTop1", newsBoard);
        model.addAttribute("exerTop1", exerBoard);
        model.addAttribute("foodTop1", foodBoard);

        if(admin != null){
            req.getSession().setAttribute("admin",admin);
            return "index";

        } else if (trainer != null) {
            req.getSession().setAttribute("trainer",trainer);
            return "index";
        } else if(user!=null){

            req.getSession().setAttribute("user",user);
            return "index";
        }
        return "index";
    }

    @GetMapping("/index_sidebar")
    public void replace(){}

    @GetMapping("/user/agree")
    public void agree(){}

    @GetMapping("/user/joinTest")
    public void joinTest(){}

    @GetMapping("/totalSearch")
    public void search(String keyword,Model model){
        System.out.println(keyword);
//        전체 보드게시글 찾기
//        List<BoardDTO> boardList = boardservice.getAllBoardList(keyword);
//        for (int i = 0; i<boardList.size(); i++){
//            System.out.println(boardList.get(i));
//        }
        //전체 보드 게시글 수 찾기
        Long boardAllCnt = boardservice.getAllsearchCnt(keyword);
        System.out.println(boardAllCnt);

//        String[] boardCategory = {"infoNews","infoExer","infoFood","infoTip"};
//        List<BoardDTO> InfoSearchList = boardservice.getSearch5List(keyword);

        //각 게시판에서 5개씩 가져오기
        List<BoardDTO> newsSearchList = boardservice.getNewsSearchList(keyword);
        List<BoardDTO> exerSearchList = boardservice.getExerSearchList(keyword);
        List<BoardDTO> foodSearchList = boardservice.getFoodSearchList(keyword);
        List<BoardDTO> tipSearchList = boardservice.getTipSearchList(keyword);
        List<BoardDTO> CommuSearchList = boardservice.getCommuSearchList(keyword);

        List<BoardDTO> matchingSearchList = MatchingService.getMachingSearchList(keyword);
        List<ChallNoticeBoardDTO> challSearchList = challService.getChallSearchList(keyword);

        model.addAttribute("newsSearchList",newsSearchList);
        model.addAttribute("exerSearchList",exerSearchList);
        model.addAttribute("foodSearchList",foodSearchList);
        model.addAttribute("tipSearchList",tipSearchList);
        model.addAttribute("commuSearchList",CommuSearchList);
        model.addAttribute("matchingSearchList",matchingSearchList);
        model.addAttribute("challSearchList",challSearchList);

    }

}
