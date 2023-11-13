package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.ChallengeService;
import com.kh.demo.service.UserMyPageService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/challenge/*")
public class ChallengeController {

    @Autowired
    private ChallengeService challService;

    @Autowired
    private UserMyPageService umpService;

    @GetMapping("#")
    public void replace(){}

    @PostMapping("myChallInfo")
    @ResponseBody
    public String myChallInfo(@RequestParam("userid") String userid,@RequestParam("choicedate") String choicedate){
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        List<MyChallengeDTO> myChallDTOList =challService.findMychall(userid,choicedate);
//        System.out.println(myChallDTOList);
        json.putPOJO("myChallDTOList",myChallDTOList);
        return json.toString();
    }


    /*재우*/
    @GetMapping("list")
    public void challList(Criteria cri, Model model) throws Exception {

        List<ChallCertBoardDTO> list = challService.getChallList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(challService.getTotal(cri), cri));
        model.addAttribute("newly_board",challService.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",challService.getReplyCntList(list));
        model.addAttribute("recent_reply",challService.getRecentReplyList(list));
    }

    @GetMapping("successChall")
    @ResponseBody
    public String successChall(@RequestParam("pageNum") int pageNum, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 5);

        String challCategory = "challAll";
        String challTerm = "challengeAll";
        List<ChallNoticeBoardDTO> list = umpService.getMyChallenge(cri, userId, challCategory,challTerm);
        PageDTO pageDTO = new PageDTO(umpService.getChallengeTotal(cri, userId, challCategory, challTerm), cri);
        json.putPOJO("list", list);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("allChall")
    @ResponseBody
    public String allChall(@RequestParam("pageNum") int pageNum, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 5);

        String challCategory = "challAll";
        String challTerm = "challengeAll";
        List<ChallNoticeBoardDTO> list = umpService.getMyChallenge(cri, userId, challCategory,challTerm);
        PageDTO pageDTO = new PageDTO(umpService.getChallengeTotal(cri, userId, challCategory, challTerm), cri);
        json.putPOJO("list", list);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }



    @GetMapping("write")
    public void write(@ModelAttribute("cri") Criteria cri,String mychallNum,Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");
        ChallNoticeBoardDTO list = challService.getChallenge(userId,mychallNum);
        System.out.println("list: "+ list);
        model.addAttribute("list",list);

    }

    @PostMapping("write")
    public String write(ChallCertBoardDTO chall, MultipartFile[] files, Criteria cri) throws Exception{
        Long boardNum = 0l;
        System.out.println("chall: "+chall);
        if(challService.regist(chall, files)) {
            boardNum = challService.getLastNum(chall.getUserId());
            return "redirect:/challenge/get"+cri.getListLink()+"&boardNum="+boardNum;
        }
        else {
            return "redirect:/challenge/list"+cri.getListLink();
        }
    }

    @GetMapping(value = {"get","modify"})
    public String get(Criteria cri, Long boardNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        model.addAttribute("cri",cri);
        HttpSession session = req.getSession();
        ChallCertBoardDTO board = challService.getDetail(boardNum);
        model.addAttribute("board",board);
        System.out.printf("board1 :"+board);
        model.addAttribute("files",challService.getFileList(boardNum));
        String loginUser = (String)session.getAttribute("loginUser");
        String requestURI = req.getRequestURI();
        if(requestURI.contains("/get")) {
            //게시글의 작성자가 로그인된 유저가 아닐 때
            if(!board.getUserId().equals(loginUser)) {
                //쿠키 검사
                Cookie[] cookies = req.getCookies();
                Cookie read_board = null;
                if(cookies != null) {
                    for(Cookie cookie : cookies) {
                        //ex) 1번 게시글을 조회하고자 클릭했을 때에는 "read_board1" 쿠키를 찾음
                        if(cookie.getName().equals("read_board"+boardNum)) {
                            read_board = cookie;
                            break;
                        }
                    }
                }
                //read_board가 null이라는 뜻은 위에서 쿠키를 찾았을 때 존재하지 않았다는 뜻
                //첫 조회거나 조회한지 1시간이 지난 후
                if(read_board == null) {
                    //조회수 증가
                    challService.updateReadCount(boardNum);
                    //read_board1 이름의 쿠키(유효기간 : 3600초)를 생성해서 클라이언트 컴퓨터에 저장
                    Cookie cookie = new Cookie("read_board"+boardNum, "r");
                    cookie.setMaxAge(3600);
                    resp.addCookie(cookie);
                }
            }
        }
        System.out.println("requestURI : "+ requestURI);
        return requestURI;
    }
    @PostMapping("modify")
    public String modify(ChallCertBoardDTO board, MultipartFile[] files, String updateCnt, Criteria cri, Model model) throws Exception {
        if(files != null){
            for (int i = 0; i < files.length; i++) {
                System.out.println("controller : "+files[i].getOriginalFilename());
            }
        }
        System.out.println("controller : "+updateCnt);
        if(challService.modify(board, files, updateCnt)) {
            return "redirect:/board/get"+cri.getListLink()+"&boardnum="+board.getBoardNum();
        }
        else {
            return "redirect:/board/list"+cri.getListLink();
        }
    }
    @PostMapping("remove")
    public String remove(Long boardnum, Criteria cri, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String loginUser = (String)session.getAttribute("loginUser");
        if(challService.remove(loginUser, boardnum)) {
            return "redirect:/board/list"+cri.getListLink();
        }
        else {
            return "redirect:/board/get"+cri.getListLink()+"&boardnum="+boardnum;
        }
    }

    @GetMapping("thumbnail")
    public ResponseEntity<Resource> thumbnail(String systemname) throws Exception{
        return challService.getThumbnailResource(systemname);
    }

    @GetMapping("file")
    public ResponseEntity<Object> download(String systemname, String orgname) throws Exception{
        return challService.downloadFile(systemname,orgname);
    }
}