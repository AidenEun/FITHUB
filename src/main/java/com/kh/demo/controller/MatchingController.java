package com.kh.demo.controller;

import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.TrainerMatchingService;
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
    @Qualifier("TrainerMatchingServiceImpl")
    private TrainerMatchingService MatchingService;

    @GetMapping("matching_list")
    public void matching_list(Criteria cri, Model model) throws Exception {
        List<TrainerMatchingBoardDTO> list = MatchingService.getmatchingList(cri);
        model.addAttribute("list", list);

       /* model.addAttribute("review_cnt_list",MatchingService.getReviewCntList(list));*/
    }

    @GetMapping("thumbnail")
    public ResponseEntity<Resource> thumbnail(String sysName) throws Exception{
        return MatchingService.getThumbnailResource(sysName);
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



    @GetMapping("/matching/matching_view")
    public String get(@RequestParam Long boardNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        List<TrainerMatchingBoardDTO> list = MatchingService.boardView(boardNum);

        for (TrainerMatchingBoardDTO board : list) {

            ProfileDTO profileInfo = MatchingService.getProfileInfo(board.getTrainerId());
            ProfileDTO careerInfo = MatchingService.getCareerInfo(board.getTrainerId());
            TrainerDTO trainerInfo = MatchingService.getTrainerInfo(board.getTrainerId());

            model.addAttribute("profileInfo", profileInfo);
            model.addAttribute("careerInfo",careerInfo);
            model.addAttribute("trainerInfo",trainerInfo);
        }
        model.addAttribute("list", list);


        if (list == null) {
            return "error";
        }

        HttpSession session = req.getSession();
        model.addAttribute("list", list);

        // foodNum에 대한 조회수 증가 처리
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

        return "matching/matching_view";
    }

    @PostMapping("profileModal")
    @ResponseBody
    public String reportModal(@RequestParam("trainerNickname") String trainerNickname) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Object userInfo = MatchingService.getUserByNickname(trainerNickname);

        if (userInfo instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) userInfo;
            json.putPOJO("userDTO", userDTO);
        }
        else if (userInfo instanceof TrainerDTO) {
            TrainerDTO trainerDTO = (TrainerDTO) userInfo;
            json.putPOJO("trainerDTO", trainerDTO);
        }
        else {
            json.put("noData", "noData");
        }
        return json.toString();
    }

    @PostMapping("u_t_matchModal")
    @ResponseBody
    public String matchingModal(@RequestParam("trainerId") String trainerId, HttpServletRequest req) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        // Retrieve board information by trainerId
        TrainerMatchingBoardDTO boardInfo = MatchingService.getBoardBytrainerId(trainerId);
        System.out.println(boardInfo);
        UTMatchingDTO utInfo = MatchingService.getutBytrainerId(trainerId);
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
    public String u_t_matching(@RequestParam("userId") String userId, @RequestParam("trainerId") String trainerId) throws Exception {
        UTMatchingDTO newMatching = new UTMatchingDTO();
        newMatching.setUserId(userId);
        newMatching.setTrainerId(trainerId);


        MatchingService.saveMatching(newMatching); // 여기서 적절한 서비스 메서드를 호출하여 데이터베이스에 insert

        return "success"; // 적절한 응답 메시지
    }


}
