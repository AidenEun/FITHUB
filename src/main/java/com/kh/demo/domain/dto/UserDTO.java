package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String user_id;
    private String user_name;
    private String user_nickname;
    private String user_pw;
    private int weight_goal;
    private int calories_goal;
    private String user_tel;
    private String user_mail;
    private int user_height;
    private int user_weight;
    private String user_gender;
    private int user_age;
    private String user_joindate;
    private Long user_point;
    private int user_reportedcnt;
}
