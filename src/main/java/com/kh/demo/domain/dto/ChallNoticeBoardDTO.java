package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ChallNoticeBoardDTO {
    private long chall_num;
    private String chall_category;
    private String chall_content;
    private int chall_term;
    private String regdate;
}
