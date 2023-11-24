package com.kh.demo.controller;

import com.kh.demo.domain.dto.AdminDTO;
import com.kh.demo.domain.dto.TrainerDTO;
import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;


@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService service;

    @Autowired
    @Qualifier("TrainerServiceImpl")
    private TrainerService serviceTrainer;

    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService serviceAdmin;

    @Autowired
    @Qualifier("UserMyPageServiceImpl")
    private UserMyPageService umpservice;

    @Autowired
    @Qualifier("TrainerMyPageServiceImpl")
    private TrainerMyPageService tmpservice;

    public UserController() {
    }


    @GetMapping("join")
    public void replace() {}

    @GetMapping("useAgree")
    public void showUseAgreePage(){}

    @GetMapping("policyAgree")
    public void showPolicyAgreePage() {}

    @PostMapping("join")
    @ResponseBody
    public String join(@RequestBody UserDTO user, RedirectAttributes ra) {
        System.out.println(user);
        if(service.join(user)) {
            ra.addAttribute("joinid",user.getUserId());
            return "true";
        }
        return "false";
    }
    @GetMapping("login")
    public void replaceLogin(){}

    @PostMapping("login")
    @ResponseBody
    public String login(String userid, String userpw, HttpServletRequest req) {
        Object loginUser = service.login(userid, userpw);

        if (loginUser != null) {
            // 로그인 성공 시 추가 작업을 수행할 수 있습니다.
            LocalDate todaydate = LocalDate.now();

            if (loginUser instanceof AdminDTO) {
                AdminDTO admin = serviceAdmin.getDetail(((AdminDTO) loginUser).getAdminId());
                req.getSession().setAttribute("loginUser", ((AdminDTO) loginUser).getAdminId());
                req.getSession().setAttribute("admin", admin);
                req.getSession().setAttribute("todaydate", todaydate);
            } else if (loginUser instanceof TrainerDTO) {
                TrainerDTO trainer = serviceTrainer.getDetail(((TrainerDTO) loginUser).getTrainerId());
                req.getSession().setAttribute("loginUser", ((TrainerDTO) loginUser).getTrainerId());
                req.getSession().setAttribute("trainer", trainer);
                req.getSession().setAttribute("profile", tmpservice.getProFileList(trainer.getTrainerId()));
                req.getSession().setAttribute("todaydate", todaydate);
            } else if (loginUser instanceof UserDTO) {
                UserDTO user = service.getDetail(((UserDTO) loginUser).getUserId());
                req.getSession().setAttribute("loginUser", ((UserDTO) loginUser).getUserId());
                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("profile", umpservice.getProFileList(user.getUserId()));
                req.getSession().setAttribute("todaydate", todaydate);
                System.out.println("회원:" + user.getUserNickname());
            }

            return "success";
        } else {
            return "failure";
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("checkid")
    @ResponseBody
    public String checkId(String userid) {
        if(service.checkId(userid)) {
            return "O";
        }
        return "X";
    }

    @GetMapping("nickname")
    @ResponseBody
    public String checkNickname (String userNickname) {
        if(service.checkNickname(userNickname)) {
            return "O";
        } else {
            return "X";
        }
    }
}

