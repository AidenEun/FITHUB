package com.kh.demo.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kh.demo.domain.dto.MyChallengeDTO;
import com.kh.demo.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/challenge/*")
public class ChallengeController {

    @Autowired
    private ChallengeService challService;

    @GetMapping("#")
    public void replace(){}

    @PostMapping("myChallInfo")
    @ResponseBody
    public String myChallInfo(@RequestParam("userid") String userid){
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        List<MyChallengeDTO> myChallDTOList =challService.findMychall(userid);
        System.out.println(myChallDTOList);
        json.putPOJO("myChallDTOList",myChallDTOList);
        return json.toString();
    }

}
