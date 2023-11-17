package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.BoardService;
import com.kh.demo.service.ChallengeService;
import com.kh.demo.service.TrainerMatchingService;
import com.kh.demo.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/searchpage/*")
public class SearchController {


    @Autowired
    @Qualifier("BoardServiceImpl")
    private BoardService boardservice;

    @Autowired
    @Qualifier("TrainerMatchingServiceImpl")
    private TrainerMatchingService tmatchingService;

    @Autowired
    private ChallengeService challService;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService tservice;

    @GetMapping("/totalSearch")
    public void search(String keyword, Model model){
        //인기게시글 띄우기
        List<BoardDTO> boardTop5List = boardservice.getBoardTop5List();

        // 트레이너 랭킹
        List<TrainerDTO> trainerTop5List= tservice.getTrainerTop5List();

        //각 게시판에서 글 가져오기

        List<BoardDTO> infoSearchList = boardservice.getinfoSearchList(keyword);
        List<BoardDTO> tipSearchList = boardservice.getTipSearchList(keyword);
        List<BoardDTO> commuSearchList = boardservice.getCommuSearchList(keyword);
        System.out.println(commuSearchList.size());

        List<TrainerMatchingBoardDTO> matchingSearchList = tmatchingService.getMachingSearchList(keyword);
        List<ChallNoticeBoardDTO> challSearchList = challService.getChallSearchList(keyword);

        //전체 보드 테이블 게시글 수 찾기
        Long boardAllCnt = boardservice.getAllsearchCnt(keyword) + matchingSearchList.size()+challSearchList.size();
        System.out.println("총 게시글"+boardAllCnt);


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
        model.addAttribute("keyword",keyword);


    }
    @GetMapping("searchinfo")
    @ResponseBody
    public String searchInfo(@RequestParam("pageNum") int pageNum, @RequestParam("keyword") String keyword, @RequestParam("currentCategory")String currentCategory) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        System.out.println("검색 진행 컨트롤러 :" + keyword);

        Criteria cri = new Criteria();
        cri.setPagenum(pageNum);
        cri.setKeyword(keyword);
        cri.setStartrow((pageNum - 1) * 12);
        cri.setAmount(12);
        cri.setBoardCategory(currentCategory);

        List<BoardDTO> list = boardservice.get12infoSearchList(cri);

        Long boardtotal = boardservice.getinfoSearchCnt(cri);

        PageDTO pageDTO = new PageDTO(boardtotal, cri);


        json.putPOJO("boardDTO", list);
        json.putPOJO("pageDTO", pageDTO);
        json.putPOJO("currentCategory",currentCategory);


        return json.toString();
    }
    @GetMapping("searchtip")
    @ResponseBody
    public String searchtip(@RequestParam("pageNum") int pageNum, @RequestParam("keyword") String keyword,@RequestParam("currentCategory")String currentCategory) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        Criteria cri = new Criteria();
        cri.setPagenum(pageNum);
        cri.setKeyword(keyword);
        cri.setStartrow((pageNum - 1) * 12);
        cri.setAmount(12);
        cri.setBoardCategory(currentCategory);

        List<BoardDTO> list = boardservice.get12TipSearchList(cri);

        Long boardtotal = boardservice.getTipSearchTotalCnt(cri);

        PageDTO pageDTO = new PageDTO(boardtotal, cri);

        json.putPOJO("boardDTO", list);
        json.putPOJO("pageDTO", pageDTO);
        json.putPOJO("currentCategory",currentCategory);

        return json.toString();
    }
    @GetMapping("searchcommu")
    @ResponseBody
    public String searchcommu(@RequestParam("pageNum") int pageNum, @RequestParam("keyword") String keyword,@RequestParam("currentCategory")String currentCategory) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        Criteria cri = new Criteria();
        cri.setPagenum(pageNum);
        cri.setKeyword(keyword);
        cri.setStartrow((pageNum - 1) * 12);
        cri.setAmount(12);
        cri.setBoardCategory(currentCategory);

        List<BoardDTO> list = boardservice.get12CommuSearchList(cri);

        Long boardtotal = boardservice.getCommuTotalCnt(cri);

        PageDTO pageDTO = new PageDTO(boardtotal, cri);

        json.putPOJO("boardDTO", list);
        json.putPOJO("pageDTO", pageDTO);
        json.putPOJO("currentCategory",currentCategory);

        return json.toString();
    }
    @GetMapping("searchmatching")
    @ResponseBody
    public String searchmatching(@RequestParam("pageNum") int pageNum, @RequestParam("keyword") String keyword,@RequestParam("currentCategory")String currentCategory) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        Criteria cri = new Criteria();
        cri.setPagenum(pageNum);
        cri.setKeyword(keyword);
        cri.setStartrow((pageNum - 1) * 12);
        cri.setAmount(12);
        cri.setBoardCategory(currentCategory);

        List<TrainerMatchingBoardDTO> list = tmatchingService.get12matchingSearchList(cri);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Long boardtotal = tmatchingService.getmatchingTotal(cri);

        PageDTO pageDTO = new PageDTO(boardtotal, cri);

        json.putPOJO("boardDTO", list);
        json.putPOJO("pageDTO", pageDTO);
        json.putPOJO("currentCategory",currentCategory);

        return json.toString();
    }
    @GetMapping("searchchall")
    @ResponseBody
    public String searchchall(@RequestParam("pageNum") int pageNum, @RequestParam("keyword") String keyword,@RequestParam("currentCategory")String currentCategory) {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        Criteria cri = new Criteria();
        cri.setPagenum(pageNum);
        cri.setKeyword(keyword);
        cri.setStartrow((pageNum - 1) * 12);
        cri.setAmount(12);
        cri.setBoardCategory(currentCategory);

        List<ChallNoticeBoardDTO> list = challService.get12challSearchList(cri);

        Long boardtotal = challService.getchallTotal(cri);

        PageDTO pageDTO = new PageDTO(boardtotal, cri);

        json.putPOJO("boardDTO", list);
        json.putPOJO("pageDTO", pageDTO);
        json.putPOJO("currentCategory",currentCategory);

        return json.toString();
    }
}
