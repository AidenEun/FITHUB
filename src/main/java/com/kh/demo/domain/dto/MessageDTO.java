package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private long messageNum;
    private String category;
    private String messageContent;
    private String sendId;
    private String receiveId;
    private String sendDate;


}
