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
@RequestMapping("/community/*")
public class CommunityController {

    @Autowired @Qualifier("BoardServiceImpl")
    private BoardService service;

    @GetMapping("commu_list")
    public void commu_list(Criteria cri, Model model) throws Exception{

        List<BoardDTO> freeList = service.getCommuFreeList(cri);
        List<BoardDTO> exerList = service.getCommuExerList(cri);
        List<BoardDTO> foodList = service.getCommuFoodList(cri);
        List<BoardDTO> galleryList = service.getCommuGalleryList(cri);
        List<BoardDTO> qnaList = service.getCommuQnaList(cri);
        model.addAttribute("freeList",freeList);
        model.addAttribute("exerList",exerList);
        model.addAttribute("foodList",foodList);
        model.addAttribute("galleryList",galleryList);
        model.addAttribute("qnaList",qnaList);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
    }

    @GetMapping("commu_free")
    public void commu_free_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("commuFree");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }

    @GetMapping("commu_exercise")
    public void commu_exercise_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("commuExer");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }

    @GetMapping("commu_food")
    public void commu_food_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("commuFood");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }

    @GetMapping("commu_gallery")
    public void commu_gallery_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("commuGallery");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }

    @GetMapping("commu_qna")
    public void commu_qna_list(Criteria cri, Model model) throws Exception{
        cri.setBoardCategory("commuQna");
        List<BoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }

    @GetMapping("commu_write")
    public void commu_write(@ModelAttribute("cri") Criteria cri, Model model){
    }

    @PostMapping("commu_write")
    public String commu_write(BoardDTO board, MultipartFile[] files, MultipartFile[] files2, Criteria cri) throws Exception{

        Long boardNum = 0l;	//long 타입의 0(0+l)
        if(service.regist(board, files, files2)) {
            boardNum = service.getLastNum(board.getUserId());
            return "redirect:/community/commu_get"+cri.getListLink()+"&boardNum="+boardNum;
        } //성공시 게시글 보는 페이지로 이동(commu_get.html)
        else {
            return "redirect:/community/commu_list"+cri.getListLink();
        } //실패시 게시글 목록 페이지로 이동
    }

    @GetMapping(value = {"commu_get","commu_modify"})
    public String get(String boardCategory, Criteria cri, Long boardNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        model.addAttribute("cri",cri);
        HttpSession session = req.getSession();
        BoardDTO board = service.getDetail(boardNum);
        model.addAttribute("board",board);
        model.addAttribute("files",service.getFileList(boardNum, boardCategory));
        model.addAttribute("files2",service.getFileList(boardNum, boardCategory));
        String loginUser = (String)session.getAttribute("loginUser");
        String requestURI = req.getRequestURI();
        if(requestURI.contains("/commu_get")) {
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

    @PostMapping("commu_modify")
    public String commu_modify(String boardCategory, BoardDTO board, MultipartFile[] files, MultipartFile[] files2, String updateCnt, Criteria cri, Model model) throws Exception {
        if(files != null){
            for (int i = 0; i < files.length; i++) {
                System.out.println("controller : "+files[i].getOriginalFilename());
            }
        }
        System.out.println("controller : "+updateCnt);
        if(service.modify(board, files, files2, updateCnt, boardCategory)) {
            return "redirect:/community/commu_get"+cri.getListLink()+"&boardNum="+board.getBoardNum();
        }
        else {
            return "redirect:/community/commu_list"+cri.getListLink();
        }
    }
    @PostMapping("remove")
    public String remove(String boardCategory, Long boardNum, Criteria cri, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String loginUser = (String)session.getAttribute("loginUser");
        if(service.remove(loginUser, boardNum, boardCategory)) {
            return "redirect:/community/commu_list"+cri.getListLink();
        }
        else {
            return "redirect:/community/commu_get"+cri.getListLink()+"&boardNum="+boardNum;
        }
    }

    @GetMapping("thumbnail")
    public ResponseEntity<Resource> thumbnail(String sysName) throws Exception{
        return service.getThumbnailResource(sysName);
    }

    @GetMapping("file")
    public ResponseEntity<Object> download(String systemname, String orgname) throws Exception{
        return service.downloadFile(systemname,orgname);
    }

}
