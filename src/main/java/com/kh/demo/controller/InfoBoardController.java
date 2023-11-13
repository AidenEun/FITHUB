package com.kh.demo.controller;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.service.BoardService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/info/*")
public class InfoBoardController {

    @Autowired @Qualifier("BoardServiceImpl")
    private BoardService service;



    @GetMapping("info_news")
    public void info_news_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("infoNews");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }


    @GetMapping("info_exercise")
    public void info_exer_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("infoExer");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }


    @GetMapping("info_food")
    public void info_food_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("infoFood");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }

    @GetMapping("info_tip")
    public void info_tip_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("infoTip");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }


    @GetMapping("info_list")
    public void info_list(Criteria cri, Model model) throws Exception{

        List<BoardDTO> newsList = service.getNewsList(cri);
        List<BoardDTO> exerList = service.getExerList(cri);
        List<BoardDTO> foodList = service.getFoodList(cri);
        List<BoardDTO> tipList = service.getTipList(cri);
        model.addAttribute("newsList",newsList);
        model.addAttribute("exerList",exerList);
        model.addAttribute("foodList",foodList);
        model.addAttribute("tipList",tipList);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));

    }

    @GetMapping("info_write")
    public void info_write(@ModelAttribute("cri") Criteria cri, Model model){
    }

    @PostMapping("info_write")
    public String info_write(BoardDTO board, MultipartFile[] files, Criteria cri) throws Exception{
        Long boardnum = 0l;	//long 타입의 0(0+l)
        if(service.regist(board, files)) {
            boardnum = service.getLastNum(board.getUserId());
            return "redirect:/info/info_get"+cri.getListLink()+"&boardnum="+boardnum;
        } //성공시 게시글 보는 페이지로 이동(info_get.html)
        else {
            return "redirect:/info/info_list"+cri.getListLink();
        } //실패시 게시글 목록 페이지로 이동
    }

    @GetMapping(value = {"info_get","info_modify"})
    public String get(String category,Criteria cri, Long boardNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        model.addAttribute("cri",cri);
        HttpSession session = req.getSession();
        BoardDTO board = service.getDetail(boardNum);
        model.addAttribute("board",board);
        model.addAttribute("files",service.getFileList(boardNum, category));
        String loginUser = (String)session.getAttribute("loginUser");
        String requestURI = req.getRequestURI();
        if(requestURI.contains("/info_get")) {
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
                    service.updateReadCount(boardNum);
                    //read_board1 이름의 쿠키(유효기간 : 3600초)를 생성해서 클라이언트 컴퓨터에 저장
                    Cookie cookie = new Cookie("read_board"+boardNum, "r");
                    cookie.setMaxAge(3600);
                    resp.addCookie(cookie);
                }
            }
        }
        return requestURI;
    }

    @GetMapping("thumbnail")
    public ResponseEntity<Resource> thumbnail(String sysName) throws Exception{
        return service.getThumbnailResource(sysName);
    }




}
