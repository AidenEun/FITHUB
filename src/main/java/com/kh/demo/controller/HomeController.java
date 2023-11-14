package com.kh.demo.controller;

import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.TrainerMapper;
import com.kh.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService tservice;

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

        List<TrainerDTO> trainerTopList = serviceTrainer.getTrainerTopList();

        model.addAttribute("trainerTopList", trainerTopList);

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
//        System.out.println(keyword);

        //인기게시글 띄우기
        List<BoardDTO> boardTop5List = boardservice.getBoardTop5List();

        // 트레이너 랭킹
        List<TrainerDTO> trainerTop5List= tservice.getTrainerTop5List();
        //전체 보드 게시글 수 찾기
        Long boardAllCnt = boardservice.getAllsearchCnt(keyword);
        System.out.println(boardAllCnt);

        //각 게시판에서 글 가져오기

        List<BoardDTO> infoSearchList = boardservice.getinfoSearchList(keyword);

        List<BoardDTO> tipSearchList = boardservice.getTipSearchList(keyword);
        List<BoardDTO> commuSearchList = boardservice.getCommuSearchList(keyword);

        List<BoardDTO> matchingSearchList = MatchingService.getMachingSearchList(keyword);
        List<ChallNoticeBoardDTO> challSearchList = challService.getChallSearchList(keyword);

        int[] boardCntArr ={infoSearchList.size(),
                tipSearchList.size(),commuSearchList.size(),matchingSearchList.size(),challSearchList.size()};

//        model.addAttribute("newsSearchList",newsSearchList);
//        model.addAttribute("exerSearchList",exerSearchList);
//        model.addAttribute("foodSearchList",foodSearchList);
        model.addAttribute("infoSearchList",infoSearchList);
        model.addAttribute("tipSearchList",tipSearchList);
        model.addAttribute("commuSearchList",commuSearchList);
        model.addAttribute("matchingSearchList",matchingSearchList);
        model.addAttribute("challSearchList",challSearchList);
        model.addAttribute("boardAllCnt",boardAllCnt);
        model.addAttribute("trainerTop5List",trainerTop5List);
        model.addAttribute("boardTop5List",boardTop5List);
        model.addAttribute("boardCntArr",boardCntArr);


    }

}
