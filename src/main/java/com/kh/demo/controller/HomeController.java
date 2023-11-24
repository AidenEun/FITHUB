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
import java.util.ArrayList;
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

    @Autowired
    private CalorieService calorieService;


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

        LocalDate todaydate = LocalDate.now();
        System.out.println("todaydate: "+todaydate);
        model.addAttribute("todaydate", todaydate);

        UserDTO user = serviceUser.getDetail(loginUser);
//        DiaryDTO diary = umpservice.getDiaryDetail(todayDiary.getRegdate(), loginUser);

        if (user != null) {
            DiaryDTO diary = umpservice.getTodayDiary(loginUser);
            if (diary != null) {
                //오늘의 비교 계산
                double resultweight = Math.round((user.getWeightGoal() - diary.getTodayWeight()) * 100.0) / 100.0;
                //오늘 칼로리 계산
                double totalResult = Math.round(diary.getFoodCalories() - diary.getExerCalories()) * 100.0 / 100.0;

                //각 음식, 운동 num 번호가 있는 배열
                String bfNumlist = diary.getTodayBreakfast();
                String lunchNumlist = diary.getTodayLunch();
                String dinnerNumlist = diary.getTodayDinner();
                String snackNumlist = diary.getTodaySnack();
                String exerNumlist = diary.getTodayExer();

                String[] todayAlllist = {bfNumlist, lunchNumlist, dinnerNumlist, snackNumlist, exerNumlist};
                String breakfast = "";
                String lunch = "";
                String dinner = "";
                String snack = "";
                String exer = "";
                double bfCal = 0d;
                double lunchCal = 0d;
                double dinnerCal = 0d;
                double snackCal = 0d;


                for (int i = 0; i < todayAlllist.length; i++) {
    //            System.out.println(todayAlllist[i]);
                    if (todayAlllist[i].isEmpty()) {
    //                System.out.println("빈문자열"+ i);
                    } else {
                        if (i == 0) {
                            String[] bfArr = todayAlllist[i].split(",");
                            ArrayList<String> bfnameList = new ArrayList<String>();
                            for (String data : bfArr) {
                                List<FoodDTO> bfDTOList = calorieService.findfoodName(data);
                                for (FoodDTO dtoData : bfDTOList) {
                                    bfnameList.add(dtoData.getFoodName());
                                    bfCal += Double.parseDouble(dtoData.getFoodCalories());

                                }
                            }
                            bfCal = Math.round(bfCal * 100.0) / 100.0;
                            for (int j = 0; j < bfnameList.size(); j++) {
                                String breakfast1 = "";
                                if (j == bfnameList.size() - 1) {
                                    breakfast1 += bfnameList.get(j);
                                } else {
                                    breakfast1 += (bfnameList.get(j) + ",");
                                }
                                breakfast += breakfast1;

                            }

                        } else if (i == 1) {
                            String[] lunchArr = todayAlllist[i].split(",");
                            ArrayList<String> lunchnameList = new ArrayList<String>();
                            for (String data : lunchArr) {
                                List<FoodDTO> lunchDTOList = calorieService.findfoodName(data);
                                for (FoodDTO dtoData : lunchDTOList) {
                                    lunchnameList.add(dtoData.getFoodName());
                                    lunchCal += Double.parseDouble(dtoData.getFoodCalories());
                                }
                            }
                            lunchCal = Math.round(lunchCal * 100.0) / 100.0;
                            for (int j = 0; j < lunchnameList.size(); j++) {
                                String lunch1 = "";
                                if (j == lunchnameList.size() - 1) {
                                    lunch1 += lunchnameList.get(j);
                                } else {
                                    lunch1 += (lunchnameList.get(j) + ",");
                                }
                                lunch += lunch1;

                            }
                        } else if (i == 2) {
                            String[] dinnerArr = todayAlllist[i].split(",");
                            ArrayList<String> dinnernameList = new ArrayList<String>();
                            for (String data : dinnerArr) {
                                List<FoodDTO> dinnerDTOList = calorieService.findfoodName(data);
                                for (FoodDTO dtoData : dinnerDTOList) {
                                    dinnernameList.add(dtoData.getFoodName());
                                    dinnerCal += Double.parseDouble(dtoData.getFoodCalories());
                                }
                            }
                            dinnerCal = Math.round(dinnerCal * 100.0) / 100.0;
                            for (int j = 0; j < dinnernameList.size(); j++) {
                                String dinner1 = "";
                                if (j == dinnernameList.size() - 1) {
                                    dinner1 += dinnernameList.get(j);
                                } else {
                                    dinner1 += (dinnernameList.get(j) + ",");
                                }
                                dinner += dinner1;

                            }
                        } else if (i == 3) {
                            String[] snackArr = todayAlllist[i].split(",");
                            ArrayList<String> snacknameList = new ArrayList<String>();
                            for (String data : snackArr) {
                                List<FoodDTO> snackDTOList = calorieService.findfoodName(data);
                                for (FoodDTO dtoData : snackDTOList) {
                                    snacknameList.add(dtoData.getFoodName());
                                    snackCal += Double.parseDouble(dtoData.getFoodCalories());
                                }
                            }
                            snackCal = Math.round(snackCal * 100.0) / 100.0;
                            for (int j = 0; j < snacknameList.size(); j++) {
                                String snack1 = "";
                                if (j == snacknameList.size() - 1) {
                                    snack1 += snacknameList.get(j);
                                } else {
                                    snack1 += (snacknameList.get(j) + ",");
                                }
                                snack += snack1;

                            }
                        } else if (i == 4) {
                            String[] exerArr = todayAlllist[i].split(",");
                            ArrayList<String> exernameList = new ArrayList<String>();
                            for (String data : exerArr) {
                                List<ExerciseDTO> exerDTOList = calorieService.findExecName(data);
                                for (ExerciseDTO dtoData : exerDTOList) {
                                    exernameList.add(dtoData.getExecName());
                                }
                            }
                            for (int j = 0; j < exernameList.size(); j++) {
                                String exer1 = "";
                                if (j == exernameList.size() - 1) {
                                    exer1 += exernameList.get(j);
                                } else {
                                    exer1 += (exernameList.get(j) + ",");
                                }
                                exer += exer1;

                            }
                        } else {
                            System.out.println("해당사항없음 오류");
                        }

                    }
                }
                double[] foodCalArr = {bfCal, lunchCal, dinnerCal, snackCal};
                String[] listNameArr = {breakfast, lunch, dinner, snack, exer};
                String[] sccChall;
                //진행중인 챌린지 내역
                List<MyChallengeDTO> myChallINGDTOList = challService.findMychall(diary.getUserId(), diary.getRegdate());
                //성공한 챌린지 내역
    //        System.out.println("성공한 챌린지 번호 : "+diary.getMyChallNum());


            model.addAttribute("resultweight", resultweight);
            model.addAttribute("listNameArr", listNameArr);
            model.addAttribute("foodCalArr", foodCalArr);
            model.addAttribute("totalResult", totalResult);
            model.addAttribute("myChallINGDTOList", myChallINGDTOList);
            model.addAttribute("sccChallNum", diary.getMyChallNum());
            }
            model.addAttribute("diary", diary);
        }
        model.addAttribute("user", user);

        List<ChallNoticeBoardDTO> challNoticeBoardList = challService.getChallNoticeBoardList(20);
        model.addAttribute("challNoticeBoardList",challNoticeBoardList);

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
