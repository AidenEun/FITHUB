package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.TrainerMyPageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trainermypage/*")
public class TrainerMyPageController {

    @Autowired
    @Qualifier("TrainerMyPageServiceImpl")
    private TrainerMyPageService service;




    @GetMapping(value = {"trainer_myprofile","trainer_profile","trainer_profile_modify"})
    public String trainer_profile(Criteria cri, String trainerId , Model model, HttpServletRequest req) throws Exception {
        cri = new Criteria(cri.getPagenum(), 4);
        String requestURI = req.getRequestURI();
        System.out.println("requestURI : "+requestURI);

        if(!requestURI.equals("/trainermypage/trainer_profile")){
            HttpSession session = req.getSession();
            trainerId = (String) session.getAttribute("loginUser");
        }

        TrainerDTO id = service.getUserDetail(trainerId);

        List<BoardDTO>  list = service.getBoardMyList(cri, trainerId);
        model.addAttribute("list", list);
        model.addAttribute("trainer", id);
        System.out.println("trainerid"+id);
        model.addAttribute("subPageMaker", new PageDTO(service.getScribeTotal(cri, trainerId), cri));


        model.addAttribute("Profiles",service.getProFileList(trainerId));

        model.addAttribute("files",service.getFileList(trainerId));
        System.out.println("Profiles"+service.getProFileList(trainerId));
        System.out.println("files"+service.getFileList(trainerId));

        model.addAttribute("pageMaker", new PageDTO(service.getBoardTotal(cri, trainerId), cri));
        model.addAttribute("newly_board", service.getBoardNewlyList(list));
        model.addAttribute("reply_cnt_list", service.getBoardReplyCntList(list));
        model.addAttribute("recent_reply", service.getBoardRecentReplyList(list));

        if (requestURI.equals("/trainermypage/trainer_myprofile")){
            return "/trainermypage/trainer_profile";
        }

        return requestURI;
    }


    @PostMapping("trainer_myprofile_modify")
    public String trainer_myprofile_modify(TrainerDTO trainerdto,String updateCnt , Model model , MultipartFile[] files) throws IOException {
        System.out.println(trainerdto);
        if(files != null){
            for (int i = 0; i < files.length; i++) {
                System.out.println("controller : "+files[i].getOriginalFilename());
            }
        }
        if (service.trainer_modify(trainerdto,files,updateCnt)){
            return "redirect:/trainermypage/trainer_myprofile";
        }
        else {
            return "redirect:/";
        }
    }

    @GetMapping("thumbnail")
    public ResponseEntity<Resource> thumbnail(String sysName) throws Exception{
        return service.getThumbnailResource(sysName);
    }


    @GetMapping("trainer_challenge")
    public void trainer_challenge(String challCategory ,String challTerm ,Criteria cri, Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        String TrainerId = (String) session.getAttribute("loginUser");

        TrainerDTO id = service.getUserDetail(TrainerId);

        System.out.println("challCategory : "+challCategory);
        System.out.println("challTerm : "+challTerm);

        if(challCategory == null){
            challCategory = "challAll";
        }
        if(challTerm == null){
            challTerm = "challengeAll";
        }

        List<ChallNoticeBoardDTO> list = service.getMyChallenge(cri, id.getTrainerId(),challCategory,challTerm);
        System.out.println("list:"+list);

        model.addAttribute("list",list);
        model.addAttribute("pageMaker", new PageDTO(service.getChallengeTotal(cri,id.getTrainerId(),challCategory,challTerm), cri));
    }


    @GetMapping("trainer_mysubscribeuser")
    public void trainer_mysubscribeuser(Criteria cri, Model model, HttpServletRequest req){
        cri = new Criteria(cri.getPagenum(), 12);
        HttpSession session = req.getSession();
        String TrainerId = (String) session.getAttribute("loginUser");

        TrainerDTO id = service.getUserDetail(TrainerId);

        List<UserDTO> list = service.getMyScribe(cri, id.getTrainerId());
        System.out.println("list:"+list);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker", new PageDTO(service.getScribeTotal(cri,id.getTrainerId()), cri));
    }


    //북마크 번호 담을려면 보드dto랑 북마크dto 합쳐진 dto 필요
    @GetMapping("trainer_subbookmark")
    public void trainer_subbookmark(Criteria cri, Model model, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String TrainerId = (String) session.getAttribute("loginUser");

        TrainerDTO id = service.getUserDetail(TrainerId);

        List<BoardDTO> list = service.getMyBookmark(cri,id.getTrainerId());
        List<ProductBoardDTO> listProduct = service.getMyBookmarkProduct(cri,id.getTrainerId());
        System.out.println("list:"+list);
        System.out.println("listProduct:"+listProduct);
        model.addAttribute("list",list);
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("pageMaker", new PageDTO(service.getBookmarkTotal(cri,id.getTrainerId()), cri));
    }

    @GetMapping("board_info")
    @ResponseBody
    public String board_info(@RequestParam("pageNum") int pageNum, HttpServletRequest req) throws Exception{
        HttpSession session = req.getSession();
        String TrainerId = (String) session.getAttribute("loginUser");

        TrainerDTO id = service.getUserDetail(TrainerId);

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<BoardDTO> list = service.getMyBookmark(cri,id.getTrainerId());
        PageDTO pageDTO = new PageDTO(service.getBookmarkTotal(cri,id.getTrainerId()), cri);
        json.putPOJO("list", list);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("board_product")
    @ResponseBody
    public String board_product(@RequestParam("pageNum") int pageNum, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String TrainerId = (String) session.getAttribute("loginUser");

        TrainerDTO id = service.getUserDetail(TrainerId);

        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<ProductBoardDTO> list = service.getMyBookmarkProduct(cri,id.getTrainerId());
        PageDTO pageDTO = new PageDTO(service.getBookmarkProductTotal(cri,id.getTrainerId()), cri);
        json.putPOJO("list", list);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }




    @GetMapping("trainer_boardlist")
    public void trainer_boardlist(Criteria cri, Model model,HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String TrainerId = (String) session.getAttribute("loginUser");

        TrainerDTO id = service.getUserDetail(TrainerId);

        List<BoardDTO> list = service.getBoardMyList(cri,id.getTrainerId());
        /* List<BoardDTO> list = serviceBoard.getBoardList(cri);*/
        System.out.println("cri : "+cri);
        System.out.println("list : "+list);
        System.out.println("PageDTO : "+new PageDTO(service.getBoardTotal(cri, id.getTrainerId()), cri));
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getBoardTotal(cri, id.getTrainerId()), cri));
        model.addAttribute("newly_board",service.getBoardNewlyList(list));
        model.addAttribute("reply_cnt_list",service.getBoardReplyCntList(list));
        model.addAttribute("recent_reply",service.getBoardRecentReplyList(list));
    }

    @GetMapping("trainer_messagelist")
    public void trainer_messagelist(String message ,Criteria cri,Model model,HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        String TrainerId = (String) session.getAttribute("loginUser");

        TrainerDTO id = service.getUserDetail(TrainerId);

        if(message == null){
            message = "messageAll";
        }

        List<MessageDTO> list = service.getMessageMyList(cri,id.getTrainerId(),message);
        System.out.println(cri);
        System.out.println("list:"+list);
        model.addAttribute("list",list);
        model.addAttribute("pageMaker",new PageDTO(service.getMessageTotal(cri,id.getTrainerId(),message), cri));
        model.addAttribute("newly_Message",service.getMessageNewlyList(list));
    }

    @GetMapping("trainer_modify")
    public void trainer_modify(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        TrainerDTO user = service.getUserDetail(loginUser);
        model.addAttribute("user", user);
    }

    @GetMapping("trainer_myinfo_modify")
    public void trainer_myinfo_modify(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();
        String loginUser = (String) session.getAttribute("loginUser");
        TrainerDTO user = service.getUserDetail(loginUser);
        model.addAttribute("user", user);
    }


    @PostMapping("trainer_myinfo_modify")
    public String trainer_myinfo_modify(TrainerDTO trainerdto, Model model) {
        System.out.println(trainerdto);
        if (service.user_modify(trainerdto)){
            TrainerDTO user = service.getUserDetail(trainerdto.getTrainerId());
            model.addAttribute("user", user);
            return "redirect:/trainermypage/trainer_myinfo";
        }
        else {
            return "redirect:/";
        }
    }


}
