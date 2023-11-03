package com.kh.demo.domain.dto;

import lombok.Data;

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
    private String userJoindate;
    private Long userPoint;
    private int userReportedcnt;
    private String userCategory;
    private int userAge;
    //재우
    private  String subDate;
}
