package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class TrainerDTO {
    private String trainer_id;
    private String trainer_name;
    private String trainer_nickname;
    private  String trainer_pw;
    private String trainer_tel;
    private String trainer_mail;
    private String trainer_zipcode;
    private String trainer_addr;
    private String trainer_addrdetail;
    private String trainer_addretc;
    private int trainer_height;
    private int trainer_weight;
    private String trainer_gender;
    private int trainer_age;
    private String trainer_part;
    private String trainer_career;
    private String trainer_intro;
    private String trainer_joindate;
    private String transferdate;
    private int trainer_reportedcnt;
}
