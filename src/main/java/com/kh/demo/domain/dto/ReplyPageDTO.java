package com.kh.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
	int replyCnt;
	List<ReplyDTO> list;
}
