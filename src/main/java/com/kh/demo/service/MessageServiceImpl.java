package com.kh.demo.service;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.MessageDTO;
import com.kh.demo.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("MessageServiceImpl")
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageMapper megmapper;



	@Override
	public boolean remove(String loginUser, Long boardnum) {
		BoardDTO board = megmapper.findByNum(boardnum);
		if(board.getUserId().equals(loginUser)) {

			return megmapper.deleteBoard(boardnum) == 1;
		}
		return false;
	}


}













