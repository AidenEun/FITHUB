package com.kh.demo.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String userNickname;
    private String userPw;
    private int weightGoal;
    private int caloriesGoal;
    private String userTel;
    private String userMail;
    private int userHeight;
    private int userWeight;
    private String userGender;
    private String userBirth;
    private LocalDateTime userJoindate;
    private Long userPoint = 0L;
    private int userReportedcnt;
    private String userCategory;
    private int userAge;
    //재우
    private  String subDate;

    private String formattedJoindate;

}
