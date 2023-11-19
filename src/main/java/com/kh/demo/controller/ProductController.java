package com.kh.demo.controller;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.domain.dto.ProductBoardDTO;
import com.kh.demo.service.ProductBoardService;
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
@RequestMapping("/product/*")
public class ProductController {

    @Autowired @Qualifier("ProductBoardServiceImpl")
    private ProductBoardService service;
    @GetMapping("prod_list")
    public void prod_list(Criteria cri, Model model) throws Exception{

        List<ProductBoardDTO> foodList = service.getProdFoodList(cri);
        List<ProductBoardDTO> exerList = service.getProdExerList(cri);

        model.addAttribute("foodList",foodList);
        model.addAttribute("exerList",exerList);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));

    }

    @GetMapping("prod_food")
    public void prod_food_list(Criteria cri, Model model) throws Exception{
        cri.setCategory("prodFood");
        cri.setAmount(15);
        List<ProductBoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }
    @GetMapping("prod_exercise")
    public void prod_exercise_list(Criteria cri, Model model) throws Exception{
        cri.setCategory("prodExer");
        cri.setAmount(15);
        List<ProductBoardDTO> list = service.getBoardList(cri);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getTotal(cri), cri));
        model.addAttribute("newly_board",service.getNewlyBoardList(list));
        model.addAttribute("reply_cnt_list",service.getReplyCntList(list));
        model.addAttribute("recent_reply",service.getRecentReplyList(list));
    }

    @GetMapping("prod_write")
    public void prod_write(@ModelAttribute("cri") Criteria cri, Model model){
    }

    @PostMapping("prod_write")
    public String prod_write(ProductBoardDTO board, MultipartFile[] files, Criteria cri) throws Exception{

        Long boardNum = 0l;	//long 타입의 0(0+l)
        if(service.regist(board, files)) {
            boardNum = service.getLastNum(board.getAdminId());
            return "redirect:/product/prod_get"+cri.getProdBoardListLink()+"&boardNum="+boardNum;
        } //성공시 게시글 보는 페이지로 이동(commu_get.html)
        else {
            return "redirect:/product"+cri.getProdBoardListLink();
        } //실패시 게시글 목록 페이지로 이동
    }

    @GetMapping(value = {"prod_get","prod_modify"})
    public String get(String category, Criteria cri, Long boardNum, HttpServletRequest req, HttpServletResponse resp, Model model) {
        model.addAttribute("cri",cri);
        HttpSession session = req.getSession();
        ProductBoardDTO board = service.getDetail(boardNum);
        model.addAttribute("board",board);
        model.addAttribute("files",service.getFileList(boardNum, category));
        String loginUser = (String)session.getAttribute("loginUser");
        String requestURI = req.getRequestURI();
        if(requestURI.contains("/prod_get")) {
            //게시글의 작성자가 로그인된 유저가 아닐 때
            if(!board.getAdminId().equals(loginUser)) {
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

    @PostMapping("prod_modify")
    public String prod_modify(String category, ProductBoardDTO board, MultipartFile[] files, String updateCnt, Criteria cri, Model model) throws Exception {
        if(files != null){
            for (int i = 0; i < files.length; i++) {
                System.out.println("controller : "+files[i].getOriginalFilename());
            }
        }
        System.out.println("controller : "+updateCnt);
        if(service.modify(board, files, updateCnt, category)) {
            return "redirect:/product/prod_get"+cri.getProdBoardListLink()+"&boardNum="+board.getBoardNum();
        }
        else {
            return "redirect:/product"+cri.getProdBoardListLink();
        }
    }
    @PostMapping("remove")
    public String remove(String category, Long boardNum, Criteria cri, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String loginUser = (String)session.getAttribute("loginUser");
        if(service.remove(loginUser, boardNum, category)) {
            return "redirect:/product"+cri.getProdBoardListLink();
        }
        else {
            return "redirect:/product/prod_get"+cri.getProdBoardListLink()+"&boardNum="+boardNum;
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
