package com.kh.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.*;
import com.kh.demo.service.AdminMyPageService;
import com.kh.demo.service.TrainerMyPageService;
import com.kh.demo.service.UserMyPageService;
import com.kh.demo.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/adminmypage/*")
public class AdminMyPageController {
    @Autowired @Qualifier("AdminMyPageServiceImpl")
    private AdminMyPageService adminMyPageService;
    @Autowired @Qualifier("UserServiceImpl")
    private UserServiceImpl userService;
    @Autowired @Qualifier("TrainerMyPageServiceImpl")
    private TrainerMyPageService trainerMyPageService;
    @Autowired @Qualifier("UserMyPageServiceImpl")
    private UserMyPageService userMyPageService;


    @GetMapping("adminmypage_list")
    public void replaceList(Model model){
        Criteria cri = new Criteria(1, 5);
        Criteria cri2 = new Criteria(1, 3);

        List<ReportDTO> reportList = adminMyPageService.getReportList(cri);
        List<TrainerSignUpDTO> trainerSingupDTOList = adminMyPageService.getSignUpList(cri);
        List<BoardDTO> boardList = adminMyPageService.getBoardList(cri2);

        model.addAttribute("reportList" , reportList);
        model.addAttribute("signUpList", trainerSingupDTOList);
        model.addAttribute("boardList" , boardList);
    }

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

    @GetMapping("doneReport")
    @ResponseBody
    public String doneReportList(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<ReportDTO> doneReportList = adminMyPageService.getDoneReportList(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getDoneReportTotal(cri), cri);

        json.putPOJO("reportList", doneReportList);
        json.putPOJO("pageDTO", pageDTO);

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
    public String searchUser(@RequestParam("pageNum") int pageNum) throws JsonProcessingException {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<UserDTO> userList = adminMyPageService.getUserList(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getUserTotal(cri), cri);

        json.putPOJO("userList", userList);
        json.putPOJO("pageDTO", pageDTO);
        String result = json.toString();
        return result;
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

//    Message
    @GetMapping("adminmypage_message")
    public void replaceMessage(Criteria cri, Model model){
        List<MessageDTO> messageList = adminMyPageService.getMessageList(cri);

        model.addAttribute("messageList" , messageList);
        model.addAttribute("pageMaker",new PageDTO(adminMyPageService.getMessageTotal(cri), cri));
    }

    @GetMapping("allMessage")
    @ResponseBody
    public String allMessage(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<MessageDTO> messageList = adminMyPageService.getMessageList(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getMessageTotal(cri), cri);

        json.putPOJO("messageList", messageList);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("byUserMessage")
    @ResponseBody
    public String messageByUser(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<MessageDTO> messageByUser = adminMyPageService.getMessageByUser(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getMessageTotalByUser(cri), cri);

        json.putPOJO("messageList", messageByUser);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

    @GetMapping("byTrainerMessage")
    @ResponseBody
    public String messageByTrainer(@RequestParam("pageNum") int pageNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Criteria cri = new Criteria(pageNum, 10);

        List<MessageDTO> messageByTrainer = adminMyPageService.getMessageByTrainer(cri);
        PageDTO pageDTO = new PageDTO(adminMyPageService.getMessageTotalByTrainer(cri), cri);

        json.putPOJO("messageList", messageByTrainer);
        json.putPOJO("pageDTO", pageDTO);

        return json.toString();
    }

//  Modal
    @PostMapping("profileModal")
    @ResponseBody
    public String reportModal(@RequestParam("userId") String userId, HttpServletRequest req)throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        Object userInfo = adminMyPageService.getUserById(userId);

        HttpSession session = req.getSession();
        String loginUser_userId = (String) session.getAttribute("loginUser");

        if  (userInfo instanceof TrainerDTO) {
            TrainerDTO trainerDTO = (TrainerDTO) userInfo;
            json.putPOJO("trainerDTO", trainerDTO);
            json.putPOJO("loginUser_userId", loginUser_userId);
            json.putPOJO("profile", trainerMyPageService.getProFileList(trainerDTO.getTrainerId()));
        }
        else if (userInfo instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) userInfo;
            json.putPOJO("userDTO", userDTO);
            json.putPOJO("loginUser_userId", loginUser_userId);
            json.putPOJO("profile", userMyPageService.getProFileList(userDTO.getUserId()));
        }
        else {
            json.put("noData", "noData");
        }
        return json.toString();
    }

    @PostMapping("send_message")
    @ResponseBody
    public String send_message(@RequestParam("receiveId") String receiveId, @RequestParam("sendId") String sendId, @RequestParam("contents") String messageContent) throws Exception {
        MessageDTO newMessage = new MessageDTO();
        newMessage.setReceiveId(receiveId);
        newMessage.setSendId(sendId);
        newMessage.setMessageContent(messageContent);


        adminMyPageService.saveMessage(newMessage);

        return "success";
    }

    @PostMapping("reportAdminModal")
    @ResponseBody
    public String reportAdminModal(@RequestParam("reportNum") Long reportNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        ReportDTO reportDTO = adminMyPageService.getReportDTO(reportNum);

        json.putPOJO("reportDTO", reportDTO);

        return json.toString();
    }

    @PostMapping("reportConfirm")
    public ResponseEntity<String> reportConfirm(@RequestBody ReportDTO reportData, HttpSession session) {
        Long reportNum = reportData.getReportNum();
        ReportDTO reportDTO = adminMyPageService.getReportDTO(reportNum);
        String reportedUser = reportDTO.getReportedUser();
        Long boardNum = reportDTO.getReportBoardnum();
        String boardCategory = reportDTO.getBoardCategory();
        String userId = reportDTO.getUserId();

        adminMyPageService.updateReportYn(reportNum);
        adminMyPageService.updateReportedUser(reportedUser, boardNum, boardCategory);
        adminMyPageService.insertMessageDoneReport(userId);

        return ResponseEntity.ok("신고 처리가 완료 되었습니다.");
    }

    @PostMapping("reportCancel")
    public ResponseEntity<String> reportCancel(@RequestBody ReportDTO reportData, HttpSession session) {
        Long reportNum = reportData.getReportNum();
        ReportDTO reportDTO = adminMyPageService.getReportDTO(reportNum);
        Long boardNum = reportDTO.getReportBoardnum();
        String boardCategory = reportDTO.getBoardCategory();
        String userId = reportDTO.getUserId();

        adminMyPageService.updateReportYn(reportNum);
        adminMyPageService.insertMessageCancelReport(userId);

        return ResponseEntity.ok("신고 철회가 완료 되었습니다.");
    }

    @PostMapping("signUpModal")
    @ResponseBody
    public String signUpModal(@RequestParam("signupNum") Long signupNum) throws Exception {
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        TrainerSignUpDTO signUpDTO = adminMyPageService.getSignUpDTO(signupNum);
        String userId = signUpDTO.getUserId();
        List<ProfileDTO> profileDTO = adminMyPageService.getSignUpFile(userId);
        UserDTO userDTO = adminMyPageService.getUser(userId);

        json.putPOJO("profileDTO", profileDTO);
        json.putPOJO("signUpDTO", signUpDTO);
        json.putPOJO("userDTO", userDTO);

        return json.toString();
    }

    @PostMapping("signUpConfirm")
    public ResponseEntity<String> signUpConfirm(@RequestBody TrainerSignUpDTO signUpDATA){
        Long signupNum = signUpDATA.getSignupNum();
        TrainerSignUpDTO signUpDTO = adminMyPageService.getSignUpDTO(signupNum);
        String userId = signUpDTO.getUserId();
        UserDTO userDTO = adminMyPageService.getUser(userId);

        adminMyPageService.signUpConfirm(signUpDTO, userDTO);

        return ResponseEntity.ok("트레이너 전환 승인이 완료 되었습니다.");
    }

    @PostMapping("cancelSignUp")
    public ResponseEntity<String> cancelSignUp(@RequestBody TrainerSignUpDTO signUpDATA){
        Long signupNum = signUpDATA.getSignupNum();
        TrainerSignUpDTO signUpDTO = adminMyPageService.getSignUpDTO(signupNum);
        String userId = signUpDTO.getUserId();

        adminMyPageService.signUpCancel(signupNum, userId);

        return ResponseEntity.ok("트레이너 전환 거절이 완료 되었습니다.");
    }

    @PostMapping("messageModal")
    @ResponseBody
    public String messageModal(@RequestParam("messageNum") Long messageNum) throws Exception{
        ObjectNode json = JsonNodeFactory.instance.objectNode();

        MessageDTO messageDTO = adminMyPageService.getMessage(messageNum);

        json.putPOJO("messageDTO", messageDTO);
        return json.toString();
    }

    @PostMapping("messageReturn")
    public ResponseEntity<String> messageReturn(@RequestBody MessageDTO messageDATA){
        String messageContent = messageDATA.getMessageContent();
        String receiveId = messageDATA.getReceiveId();
        Long messageNum = messageDATA.getMessageNum();

        adminMyPageService.returnMessage(messageContent, receiveId, messageNum);

        return ResponseEntity.ok("문의 답변이 완료 되었습니다.");
    }
}
