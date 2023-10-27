package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class TrainerSingupDTO {
    private Long signup_num;
    private String user_id;
    private String trainer_zipcode;
    private String trainer_addr;
    private String trainer_addrdetail;
    private String trainer_addretc;
    private String trainer_part;
    private String trainer_career;
}
