package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class U_t_matchingDTO {
    private long matching_num;
    private String user_id;
    private String trainer_id;
    private String user_check;
    private String trainer_check;
    private String matching_date;
}
