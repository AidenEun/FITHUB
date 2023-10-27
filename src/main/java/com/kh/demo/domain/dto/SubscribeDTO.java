package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class SubscribeDTO {
    private Long sub_num;
    private String user_id;
    private String trainer_id;
    private String  sub_date;
}
