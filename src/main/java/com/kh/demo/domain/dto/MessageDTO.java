package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class MessageDTO {

    private long message_num;
    private String category;
    private String message_content;
    private String send_id;
    private String receive_id;
    private String send_date;
}
