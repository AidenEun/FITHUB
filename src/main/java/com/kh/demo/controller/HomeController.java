package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.TrainerMapper;
import com.kh.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService service;

    @Autowired
    @Qualifier("UserMyPageServiceImpl")
    private UserMyPageService umpservice;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService serviceTrainer;

    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService serviceAdmin;

    @Autowired @Qualifier("BoardServiceImpl")
    private BoardService boardservice;

    @Autowired @Qualifier("ProductBoardServiceImpl")
    private ProductBoardService pboardservice;

    @Autowired
    @Qualifier("TrainerMatchingServiceImpl")
    private TrainerMatchingService MatchingService;

    @Autowired
    @Qualifier("ChallengeServiceImpl")
    private ChallengeService challService;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService tservice;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService serviceUser;




    @RequestMapping("/")
    public String home(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();
        String loginUser = (String)session.getAttribute("loginUser");

        //트레이너 구독자 랭킹 1-9위
        List<TrainerDTO> trainerTopNumList = tservice.getTrainerTopNumList(9);
        model.addAttribute("trainerTopNumList",trainerTopNumList);
        System.out.println("trainerTopNumList: "+trainerTopNumList);
        //트레이너 게시글 최다작성 랭킹 1-5위
        List<TrainerDTO> trainerBoardTotalTop5List = tservice.getTrainerBoardTotalTop5List();
        model.addAttribute("trainerBoardTotalTop5List",trainerBoardTotalTop5List);
        //트레이너 게시글 댓글 최다작성 랭킹 1-5위
        List<TrainerDTO> trainerReplyTotalTop5List = tservice.getTrainerReplyTotalTop5List();
        model.addAttribute("trainerReplyTotalTop5List",trainerReplyTotalTop5List);
        //유저 게시글 최다작성 랭킹 1-5위
        List<UserDTO> userBoardTotalTop5List = serviceUser.getUserBoardTotalTop5List();
        model.addAttribute("userBoardTotalTop5List",userBoardTotalTop5List);
        //유저 게시글 댓글 최다작성 랭킹 1-5위
        List<UserDTO> userReplyTotalTop5List = serviceUser.getUserReplyTotalTop5List();
        model.addAttribute("userReplyTotalTop5List",userReplyTotalTop5List);

        //인기 게시글 랭킹 1-5위
        List<BoardDTO> userBoardLikeTop5List = boardservice.getUserBoardLikeTop5List();
        model.addAttribute("userBoardLikeTop5List",userBoardLikeTop5List);
        System.out.println("userBoardLikeTop5List: "+userBoardLikeTop5List);

        DiaryDTO todayDiary = umpservice.getTodayDiary(loginUser);
        model.addAttribute("todayDiary",todayDiary);

        List<ChallNoticeBoardDTO> challNoticeBoardList = challService.getChallNoticeBoardList(20);
        model.addAttribute("challNoticeBoardList",challNoticeBoardList);
        System.out.println("challNoticeBoardList: "+challNoticeBoardList);

//        List<BoardDTO> indexInfoBoardList = boardservice.getindexInfoBoardList();
//        model.addAttribute("indexInfoBoardList",indexInfoBoardList);
//
//        List<BoardDTO> indexcomuBoardList = boardservice.getindexInfoBoardList();
//        model.addAttribute("indexcomuBoardList",indexcomuBoardList);
//
//        List<ProductBoardDTO> indexProductBoardList = pboardservice.getindexProductBoardList();
//        model.addAttribute("indexProductBoardList",indexProductBoardList);

        BoardDTO newsBoard = boardservice.getNewsTop1();
        BoardDTO exerBoard = boardservice.getExerTop1();
        BoardDTO foodBoard = boardservice.getFoodTop1();
        model.addAttribute("newsTop1", newsBoard);
        model.addAttribute("exerTop1", exerBoard);
        model.addAttribute("foodTop1", foodBoard);

        List<TrainerDTO> trainerTopList = serviceTrainer.getTrainerTopList();
        model.addAttribute("trainerTopList", trainerTopList);
        return "index";
    }

//    @GetMapping("/index_sidebar")
//    public void replace(HttpServletRequest req, Model model) {
//        HttpSession session = req.getSession();
//        String userid = (String) session.getAttribute("loginUser");
//        String loginUser = (String) session.getAttribute("loginUser");
//        UserDTO user = umpservice.getUserDetail(loginUser);
//
////       오늘 날짜 구하기
//        LocalDate todaydate = LocalDate.now();
//
//        model.addAttribute("user", user);
//        model.addAttribute("todaydate", todaydate);
//
//    }


    @PostMapping("/infoNews")
    @ResponseBody
    public String infoNews(@RequestParam("idx") int idx , @RequestParam("category") String category) throws Exception{
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        int limit = idx*9;
        List<BoardDTO> list = boardservice.getindexInfoBoardList(limit, category);

        System.out.println("list: "+list);
        json.putPOJO("list", list);

        return json.toString();
    }
    @PostMapping("/infoTip")
    @ResponseBody
    public String infoTip(@RequestParam("idx") int idx , @RequestParam("category") String category) throws Exception{
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        int limit = idx*9;
        List<BoardDTO> list = boardservice.getindexInfoBoardList(limit, category);

        System.out.println("list: "+list);
        json.putPOJO("list", list);

        return json.toString();
    }

    @PostMapping("/info")
    @ResponseBody
    public String info(@RequestParam("idx") int idx , @RequestParam("category") String category) throws Exception{
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        int limit = idx*9;
        List<BoardDTO> list = boardservice.getindexInfoBoardList(limit, category);

        System.out.println("list: "+list);
        json.putPOJO("list", list);

        return json.toString();
    }
    @PostMapping("/commu")
    @ResponseBody
    public String commu(@RequestParam("idx") int idx , @RequestParam("category") String category) throws Exception{
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        int limit = idx*9;
        List<BoardDTO> list = boardservice.getindexInfoBoardList(limit, category);

        System.out.println("list: "+list);
        json.putPOJO("list", list);

        return json.toString();
    }
    @PostMapping("/prod")
    @ResponseBody
    public String prod(@RequestParam("idx") int idx , @RequestParam("category") String category) throws Exception{
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        int limit = idx*9;
        List<ProductBoardDTO> list = pboardservice.getindexProductBoardList(limit, category);

        System.out.println("list: "+list);
        json.putPOJO("list", list);

        return json.toString();
    }



}
