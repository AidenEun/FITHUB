package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.BoardService;
import com.kh.demo.service.ChallengeService;
import com.kh.demo.service.TrainerService;
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

    @Autowired
    @Qualifier("BoardServiceImpl")
    private BoardService boardservice;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService tservice;

    @GetMapping("#")
    public void replace(){}

    @PostMapping("myChallInfo")
    @ResponseBody
    public String myChallInfo(@RequestParam("userid") String userid,@RequestParam("choicedate") String choicedate){
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        List<MyChallengeDTO> myChallDTOList =challService.findMychall(userid,choicedate);
        System.out.println(myChallDTOList);
        json.putPOJO("myChallDTOList",myChallDTOList);
        return json.toString();
    }


    /*재우*/
    @GetMapping("list")
    public void challList(Integer pagenum, Integer amount, String type, String keyword, Integer  noticePagenum, Integer  noticeAmount
            , String challCategory, String challTerm,Model model) throws Exception {

        if (noticePagenum == null || noticePagenum <= 0) {
            noticePagenum = 1;
        }
        if(noticeAmount == null || noticeAmount <= 0){
            noticeAmount=8;
        }
        if (pagenum == null || pagenum <= 0) {
            pagenum = 1;
        }
        if(amount == null || amount <= 0){
            amount=10;
        }
        if (challCategory == null) {
            challCategory = "challAll";
        }
        if (challTerm == null) {
            challTerm = "challengeAll";
        }
        System.out.println("noticePagenum: "+noticePagenum);
        System.out.println("noticeAmount: "+noticeAmount);
        System.out.println("challCategory: "+challCategory);
        System.out.println("challTerm: "+challTerm);
        Criteria cri = new Criteria(pagenum,amount);
        cri.setType(type);
        cri.setKeyword(keyword);
        Criteria noticeCri = new Criteria(noticePagenum,noticeAmount);
        List<ChallCertBoardDTO> list = challService.getChallList(cri);
        List<ChallNoticeBoardDTO> noticeList = challService.getChallNoticeList(noticeCri, challCategory, challTerm);
        //인기게시글 띄우기
        List<BoardDTO> boardTop5List = boardservice.getBoardTop5List();
        // 트레이너 랭킹
        List<TrainerDTO> trainerTop5List= tservice.getTrainerTop5List();
        model.addAttribute("trainerTop5List",trainerTop5List);
        model.addAttribute("boardTop5List",boardTop5List);

        model.addAttribute("challCategory",challCategory);
        model.addAttribute("challTerm",challTerm);
        model.addAttribute("list",list);
        model.addAttribute("noticeList",noticeList);

        model.addAttribute("pageMaker",new PageDTO(challService.getTotal(cri), cri));
        model.addAttribute("noticePageMaker",new PageDTO(challService.getNoticeTotal(noticeCri, challCategory, challTerm), noticeCri));

        model.addAttribute("newly_board",challService.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",challService.getReplyCntList(list));
        model.addAttribute("recent_reply",challService.getRecentReplyList(list));
    }

    @PostMapping("challNoticeGet")
    @ResponseBody
    public String challNoticeGet(@RequestParam("challNum") Long challNum) throws Exception{
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        ChallNoticeBoardDTO list = challService.getChallNoticeDetail(challNum);

        System.out.println("list: "+list);
        json.putPOJO("list", list);

        return json.toString();
    }

    @PostMapping("noticeGetConfirm")
    @ResponseBody
    public String noticeGetConfirm(@RequestBody ChallCertBoardDTO challCert, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("loginUser");

        if(challService.checkChall(challCert.getChallNum(),id)){
            System.out.println("fail");
            return "fail";
        }
        else if(challService.insertMyChall(challCert.getChallNum(),id)){
            System.out.println("success");
            return "success";
        }
        else {
            System.out.println("error");
            return "error";
        }
    }

    @PostMapping("noticeGetDelete")
    public ResponseEntity<String> noticeGetDelete(@RequestBody ChallCertBoardDTO challCert) {

        challService.deleteChallNotice(challCert.getChallNum());

        return ResponseEntity.ok("성공");
    }


    @GetMapping("successChall")
//    @GetMapping("{category}")
    @ResponseBody
    public String successChall(@RequestParam("pageNum") int pageNum, HttpServletRequest req/*, @PathVariable("category")String category*/) throws Exception {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("loginUser");

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 5);

        List<ChallNoticeBoardDTO> list = umpService.getSuccessMyChallenge(userId);
        PageDTO pageDTO = new PageDTO(umpService.getSuccessMyChallengeTotal(cri, userId), cri);
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
        //인기게시글 띄우기
        List<BoardDTO> boardTop5List = boardservice.getBoardTop5List();
        // 트레이너 랭킹
        List<TrainerDTO> trainerTop5List= tservice.getTrainerTop5List();
        model.addAttribute("trainerTop5List",trainerTop5List);
        model.addAttribute("boardTop5List",boardTop5List);
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

    @PostMapping("noticeWrite")
    public String noticeWrite(ChallNoticeBoardDTO chall) throws Exception{
        System.out.println("chall: "+chall);
        if(challService.insertChallNotice(chall)) {
            return "redirect:/challenge/list";
        }
        return "redirect:/";

    }

    @GetMapping(value = {"get","modify"})
    public String get( Criteria cri, Long boardNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        model.addAttribute("cri",cri);
        String boardCategory = "challCert";
        HttpSession session = req.getSession();
        ChallCertBoardDTO board = challService.getDetail(boardNum);
        model.addAttribute("board",board);
        model.addAttribute("files",challService.getFileList(boardNum,boardCategory));
        System.out.printf("files :"+challService.getFileList(boardNum, boardCategory));
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
        //인기게시글 띄우기
        List<BoardDTO> boardTop5List = boardservice.getBoardTop5List();
        // 트레이너 랭킹
        List<TrainerDTO> trainerTop5List= tservice.getTrainerTop5List();
        model.addAttribute("trainerTop5List",trainerTop5List);
        model.addAttribute("boardTop5List",boardTop5List);
        return requestURI;
    }
    @PostMapping("modify")
    public String modify(ChallCertBoardDTO board, MultipartFile[] files, String updateCnt, Criteria cri, Model model) throws Exception {
        String boardCategory = "challCert";
        if(files != null){
            for (int i = 0; i < files.length; i++) {
                System.out.println("controller : "+files[i].getOriginalFilename());
            }
        }
        System.out.println("controller : "+updateCnt);
        if(challService.modify(board, files, updateCnt, boardCategory)) {
            return "redirect:/challenge/get"+cri.getListLink()+"&boardNum="+board.getBoardNum();
        }
        else {
            return "redirect:/challenge/list"+cri.getListLink();
        }
    }
    @PostMapping("remove")
    public String remove(Long boardNum, Criteria cri, HttpServletRequest req, String boardCategory) {
        HttpSession session = req.getSession();
        String loginUser = (String)session.getAttribute("loginUser");
        if(challService.remove(loginUser, boardNum, boardCategory)) {
            return "redirect:/challenge/list"+cri.getListLink();
        }
        else {
            return "redirect:/challenge/get"+cri.getListLink()+"&boardNum="+boardNum;
        }
    }

    @GetMapping("thumbnail")
    public ResponseEntity<Resource> thumbnail(String sysName) throws Exception{
        return challService.getThumbnailResource(sysName);
    }

    @GetMapping("file")
    public ResponseEntity<Object> download(String sysName, String orgName) throws Exception{
        return challService.downloadFile(sysName,orgName);
    }


    @GetMapping("deleteChall")
    @ResponseBody
    public void deleteChall(@RequestParam("mychallNum") Long mychallNum){
        challService.deleteMyChall(mychallNum);
    }
}