package com.kh.demo.service;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.MessageDTO;

import java.util.ArrayList;
import java.util.List;

public interface MessageService {
	//insert

	
	//delete
	public boolean remove(String loginUser, Long boardnum);

	//select
	Long getTotal(Criteria cri);
	BoardDTO getDetail(Long boardnum);
	Long getLastNum(String userid);
	ArrayList<String> getNewlyMessageList(List<MessageDTO> list) throws Exception;

	List<MessageDTO> getMyMessageList(Criteria cri, String userId);
}








