package com.kh.demo.controller;

import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.BoardService;
import com.kh.demo.service.ChallengeService;
import com.kh.demo.service.TrainerMatchingService;
import com.kh.demo.service.TrainerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/matching/*")
public class MatchingController {

    @Autowired
    private ChallengeService challService;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService tservice;

    @Autowired @Qualifier("BoardServiceImpl")
    private BoardService boardservice;

    @Autowired
    @Qualifier("TrainerMatchingServiceImpl")
    private TrainerMatchingService MatchingService;

    @Autowired @Qualifier("BoardServiceImpl")
    private BoardService service;

    @GetMapping("matching_list")
    public void matching_list(Criteria cri, Model model) throws Exception {
        List<TrainerMatchingBoardDTO> list = MatchingService.getmatchingList(cri);
        model.addAttribute("list", list);
        model.addAttribute("pageMaker",new PageDTO(MatchingService.getTotal(cri), cri));
        //인기게시글 띄우기
        List<BoardDTO> boardTop5List = boardservice.getBoardTop5List();
        // 트레이너 랭킹
        List<TrainerDTO> trainerTop5List= tservice.getTrainerTop5List();
        model.addAttribute("trainerTop5List",trainerTop5List);
        model.addAttribute("boardTop5List",boardTop5List);
       /* model.addAttribute("review_cnt_list",MatchingService.getReviewCntList(list));*/
    }

    @GetMapping("thumbnail")
    public ResponseEntity<Resource> thumbnail(String sysName) throws Exception{
        return MatchingService.getThumbnailResource(sysName);
    }

    @GetMapping("matching_write")
    public void write(@ModelAttribute("cri") Criteria cri,Model model) {
        System.out.println(cri);
        //인기게시글 띄우기
        List<BoardDTO> boardTop5List = boardservice.getBoardTop5List();
        // 트레이너 랭킹
        List<TrainerDTO> trainerTop5List= tservice.getTrainerTop5List();
        model.addAttribute("trainerTop5List",trainerTop5List);
        model.addAttribute("boardTop5List",boardTop5List);
    }

    @PostMapping("matching_write")
    public String write(TrainerMatchingBoardDTO board, Criteria cri) throws Exception{
        Long boardNum = 0l;
        if(MatchingService.regist(board)) {
            boardNum = MatchingService.getLastNum(board.getTrainerId());
            return "redirect:/matching/matching_view"+cri.getListLink()+"&boardNum="+boardNum;
        }
        else {
            return "redirect:/matching/matching_list"+cri.getListLink();
        }
    }



    @GetMapping(value = {"matching_view","matching_modify"})
    public String get(@RequestParam Long boardNum, Criteria cri, HttpServletRequest req, HttpServletResponse resp, Model model) {
        TrainerMatchingBoardDTO list = MatchingService.boardView(boardNum);
        ProfileDTO profileInfo = MatchingService.getProfileInfo(list.getTrainerId());
        List<ProfileDTO> careerInfo = MatchingService.getCareerInfo(list.getTrainerId());
        TrainerDTO trainerInfo = MatchingService.getTrainerInfo(list.getTrainerId());
        Double starRatingAv = MatchingService.getStarRatingAv(boardNum);

        HttpSession session = req.getSession();
        String loginUser = (String)session.getAttribute("loginUser");

        LikeDTO heart = new LikeDTO();
        heart = service.likeCheck(boardNum,loginUser);
        model.addAttribute("heart",heart);

        BookMarkDTO bookmark = new BookMarkDTO();
        bookmark = service.bookCheck(boardNum,loginUser);
        model.addAttribute("bookmark",bookmark);


        //인기게시글 띄우기
        List<BoardDTO> boardTop5List = boardservice.getBoardTop5List();
        // 트레이너 랭킹
        List<TrainerDTO> trainerTop5List= tservice.getTrainerTop5List();
        model.addAttribute("trainerTop5List",trainerTop5List);
        model.addAttribute("boardTop5List",boardTop5List);

        model.addAttribute("cri",cri);
        model.addAttribute("profileInfo",profileInfo);
        model.addAttribute("careerInfo",careerInfo);
        model.addAttribute("trainerInfo",trainerInfo);
        model.addAttribute("list", list);
        model.addAttribute("starRatingAv",starRatingAv);
        model.addAttribute("loginUser",loginUser);


        if (list == null) {
            return "error";
        }

        String requestURI = req.getRequestURI();
        if(requestURI.contains("/get")) {
            Cookie[] cookies = req.getCookies();
            boolean hasViewed = false;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("view_board" + boardNum)) {
                        hasViewed = true;
                        break;
                    }
                }
            }
            if (!hasViewed) {
                // 조회수 증가
                MatchingService.updateViewCount(boardNum);

                // "view_food{foodNum}" 이름의 쿠키(유효기간: 3600초)를 생성해서 클라이언트 컴퓨터에 저장
                Cookie cookie = new Cookie("view_food" + boardNum, "viewed");
                cookie.setMaxAge(3600);
                resp.addCookie(cookie);
            }
        }
        return requestURI;
    }

    @GetMapping("check")
    @ResponseBody
    public UTMatchingDTO utcheck(@RequestParam("trainerId") String trainerId, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");

        UTMatchingDTO check = MatchingService.uTtrainerCheck(trainerId, userId);
        return check;
    }





    @PostMapping("matching_modify")
    public String modify(TrainerMatchingBoardDTO board, Criteria cri, Model model) throws Exception {

        if(MatchingService.modify(board)) {
            return "redirect:/matching/matching_view"+cri.getListLink()+"&boardNum="+board.getBoardNum();
        }
        else {
            return "redirect:/matching/view"+cri.getListLink();
        }
    }

    @PostMapping("remove")
    public String remove(Long boardNum, Criteria cri, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String loginUser = (String)session.getAttribute("loginUser");
        if(MatchingService.remove(loginUser, boardNum)) {
            return "redirect:/matching/matching_list"+cri.getListLink();
        }
        else {
            return "redirect:/matching/matching_list"+cri.getListLink()+"&boardNum="+boardNum;
        }
    }


    @PostMapping("profileModal")
    @ResponseBody
    public String reportModal(@RequestParam("trainerNickname") String trainerNickname, HttpServletRequest req)throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Object userInfo = MatchingService.getUserByNickname(trainerNickname);

        HttpSession session = req.getSession();
        String loginUser_userId = (String) session.getAttribute("loginUser");

        if  (userInfo instanceof TrainerDTO) {
            TrainerDTO trainerDTO = (TrainerDTO) userInfo;
            json.putPOJO("trainerDTO", trainerDTO);
            json.putPOJO("loginUser_userId", loginUser_userId);
        }
        else if (userInfo instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) userInfo;
            json.putPOJO("userDTO", userDTO);
            json.putPOJO("loginUser_userId", loginUser_userId);
        }
        else {
            json.put("noData", "noData");
        }
        return json.toString();
    }
    @PostMapping("send_message")
    @ResponseBody
    public String u_t_matching(@RequestParam("receiveId") String receiveId, @RequestParam("sendId") String sendId, @RequestParam("contents") String contents) throws Exception {
        MessageDTO newMessage = new MessageDTO();
        newMessage.setReceiveId(receiveId);
        newMessage.setSendId(sendId);
        newMessage.setMessageContent(contents);


        MatchingService.saveMessage(newMessage);

        return "success"; // 적절한 응답 메시지
    }
    @PostMapping("u_t_matchModal")
    @ResponseBody
    public String matchingModal(@RequestParam("trainerId") String trainerId, @RequestParam("boardNum") Long boardNum, HttpServletRequest req) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        System.out.println(boardNum);
        // Retrieve board information by trainerId
        TrainerMatchingBoardDTO boardInfo = MatchingService.getBoardBytrainerId(trainerId);
        System.out.println(boardInfo);
        UTMatchingDTO utInfo = MatchingService.getutBytrainerId(trainerId);
        json.putPOJO("boardNum", boardNum);
        System.out.println(utInfo);
        if (boardInfo instanceof TrainerMatchingBoardDTO) {
            TrainerMatchingBoardDTO trainerMatchingBoardDTO = (TrainerMatchingBoardDTO) boardInfo;
            json.putPOJO("trainerMatchingBoardDTO", trainerMatchingBoardDTO);
            System.out.println(trainerMatchingBoardDTO);
            // Retrieve userId from the session
            System.out.println(userId);
            // Add userId to the JSON response
            json.putPOJO("userId", userId);

            if (utInfo != null) {
                UTMatchingDTO utMatchingDTO = (UTMatchingDTO) utInfo;
                json.putPOJO("utMatchingDTO", utMatchingDTO);
                System.out.println(utInfo);
            }
        } else {
            json.put("noData", "noData");
        }

        // Add trainerId to the JSON response
        json.put("trainerId", trainerId);
        System.out.println("JSON Response: " + json.toString());
        return json.toString();
    }

    @PostMapping("apply")
    @ResponseBody
    public String u_t_matching(@RequestParam("userId") String userId, @RequestParam("trainerId") String trainerId, @RequestParam("boardNum") Long boardNum) throws Exception {
        UTMatchingDTO newMatching = new UTMatchingDTO();
        newMatching.setUserId(userId);
        newMatching.setTrainerId(trainerId);
        newMatching.setBoardNum(boardNum);


        MatchingService.saveMatching(newMatching);

        return "success";
    }

    @GetMapping("totalSearch")
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

        List<TrainerMatchingBoardDTO> matchingSearchList = MatchingService.getMachingSearchList(keyword);
        List<ChallNoticeBoardDTO> challSearchList = challService.getChallSearchList(keyword);

        int[] boardCntArr ={infoSearchList.size(),
                tipSearchList.size(),commuSearchList.size(),matchingSearchList.size(),challSearchList.size()};

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

    @PostMapping("subscribe_click")
    @ResponseBody
    public String clickSubscription(@RequestParam("userId") String userId, @RequestParam("trainerId") String trainerId) {
        SubscribeDTO newSubscribe = new SubscribeDTO();
        newSubscribe.setUserId(userId);
        newSubscribe.setTrainerId(trainerId);

        String subscriptionResult = MatchingService.clickSubs(newSubscribe);

        if ("subscribed".equals(subscriptionResult)) {
            // 이미 구독 중인 경우
            return "subscribed";
        } else {
            // 구독하지 않은 경우
            return "unsubscribed";
        }
    }

    @GetMapping("/map")
    @ResponseBody
    public String map(@RequestParam("boardNum") Long boardNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        List<TrainerMatchingBoardDTO> boardList = MatchingService.getAllBoards(boardNum);
        //System.out.println(boardList);
        // 가져온 데이터를 JSON으로 변환하여 JSON 객체에 담습니다.
        ArrayNode arrayNode = json.putArray("boardList");
        for (TrainerMatchingBoardDTO board : boardList) {
            ObjectNode boardNode = JsonNodeFactory.instance.objectNode();
            // 필요한 정보들을 boardNode에 추가합니다.
            List<TrainerDTO> trainerInfo = MatchingService.getTrainerNickname(board.getTrainerId());
            //System.out.println(trainerInfo);
            boardNode.put("TrainerAddr", board.getTrainerAddr());
            for (TrainerDTO info : trainerInfo) {
                boardNode.put("trainerNickname", info.getTrainerNickname());
            }

            arrayNode.add(boardNode);
        }

        return json.toString();
    }




}
