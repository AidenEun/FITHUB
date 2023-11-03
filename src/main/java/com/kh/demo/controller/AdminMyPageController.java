package com.kh.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.AdminMyPageService;
import com.kh.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adminmypage/*")
public class AdminMyPageController {
    @Autowired @Qualifier("ReportServiceImpl")
    private AdminMyPageService adminMyPageService;
    @Autowired @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;

    @GetMapping("adminmypage_list")
    public void replaceList(){}

//    Report
    @GetMapping("adminmypage_report")
    public void reportList(Criteria cri, Model model) throws Exception{
        List<ReportDTO> reportList = adminMyPageService.getReportList(cri);

        model.addAttribute("reportList" , reportList);
        model.addAttribute("pageMaker",new PageDTO(adminMyPageService.getReportTotal(cri), cri));
    }

    @GetMapping("allReport")
    @ResponseBody
    public String allReportList(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<ReportDTO> reportList = adminMyPageService.getReportList(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getReportTotal(cri), cri);

        json.putPOJO("reportList", reportList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("byUserReport")
    @ResponseBody
    public String reportListByUser(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<ReportDTO> reportListByUser = adminMyPageService.getReportListByUser(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getReportTotalByUser(cri), cri);

        json.putPOJO("reportList", reportListByUser);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("byTrainerReport")
    @ResponseBody
    public String reportListByTrainer(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<ReportDTO> reportListByTrainer = adminMyPageService.getReportListByTrainer(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getReportTotalByTrainer(cri), cri);

        json.putPOJO("reportList", reportListByTrainer);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @PostMapping("reportModal")
    @ResponseBody
    public String reportModal(@RequestParam("userId") String userId) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Object userInfo = adminMyPageService.getUserById(userId);

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

//    SignUp
    @GetMapping("adminmypage_trainer")
    public void replaceTrainer(Criteria cri, Model model) throws Exception{
        List<TrainerSignUpDTO> trainerSingupDTOList = adminMyPageService.getSignUpList(cri);
        List<UserDTO> userDTOList = userService.getSignUpListInUser(cri);

        model.addAttribute("signUpList", trainerSingupDTOList);
        model.addAttribute("signUpListInUser", userDTOList);
        model.addAttribute("pageMaker",new PageDTO(adminMyPageService.getSignUpTotal(cri), cri));
    }

//    AdminBoard
    @GetMapping("adminmypage_board")
    public void replaceBoard(Criteria cri, Model model) throws Exception{
        List<BoardDTO> boardList = adminMyPageService.getBoardList(cri);

        model.addAttribute("boardList" , boardList);
        model.addAttribute("pageMaker",new PageDTO(adminMyPageService.getBoardTotal(cri), cri));

        System.out.println(new PageDTO(adminMyPageService.getBoardTotal(cri), cri));
    }

    @GetMapping("allBoard")
    @ResponseBody
    public String adminAllBoardList(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<BoardDTO> boardList = adminMyPageService.getBoardList(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getBoardTotal(cri), cri);

        json.putPOJO("boardList", boardList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("ExerBoard")
    @ResponseBody
    public String adminExerBoardList(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<BoardDTO> adminExerBoardList = adminMyPageService.getAdminExerBoard(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getExerBoardTotal(cri), cri);

        json.putPOJO("boardList", adminExerBoardList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("newsBoard")
    @ResponseBody
    public String adminNewsBoardList(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<BoardDTO> adminNewsBoardList = adminMyPageService.getAdminNewsBoard(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getNewsBoardTotal(cri), cri);

        json.putPOJO("boardList", adminNewsBoardList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("tipBoard")
    @ResponseBody
    public String adminTipBoardList(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<BoardDTO> adminTipBoardList = adminMyPageService.getAdminTipBoard(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getTipBoardTotal(cri), cri);

        json.putPOJO("boardList", adminTipBoardList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("recipeBoard")
    @ResponseBody
    public String adminRecipeBoardList(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);
        System.out.println(cri);

        List<BoardDTO> adminRecipeBoardList = adminMyPageService.getAdminRecipeBoard(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getRecipeBoardTotal(cri), cri);
        json.putPOJO("boardList", adminRecipeBoardList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

//    UserSearch
    @GetMapping("adminmypage_usersearch")
    public void replaceUserSearch(){}

    @PostMapping("adminmypage_usersearch")
    @ResponseBody
    public String userSearchData(@RequestParam("keyword") String keyword, @RequestParam("option") String option){
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        System.out.println(option);
        System.out.println(keyword);

        if (option.equals("trainer")){
            TrainerDTO trainerDTO = adminMyPageService.getTrainer(keyword);
            if(trainerDTO != null){
                json.putPOJO("trainerDTO", trainerDTO);
            }
            else {
                json.put("noData", "noData");
            }
        }
        else if (option.equals("user")){
            UserDTO userDTO = adminMyPageService.getUser(keyword);
            if (userDTO != null){
                json.putPOJO("userDTO", userDTO);
            }
            else {
                json.put("noData", "noData");
            }
        }

        return json.toString();
    }

    @GetMapping("searchUser")
    @ResponseBody
    public String searchUser(@RequestParam("pageNum") int pageNum){
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<UserDTO> userList = adminMyPageService.getUserList(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getUserTotal(cri), cri);

        json.putPOJO("userList", userList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }
    @GetMapping("searchTrainer")
    @ResponseBody
    public String searchTrainer(@RequestParam("pageNum") int pageNum){
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<TrainerDTO> trainerList = adminMyPageService.getTrainerList(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getTrainerTotal(cri), cri);

        json.putPOJO("trainerList", trainerList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("adminmypage_message")
    public void replaceMessage(){}

}
