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

	@Override
	public Long getTotal(Criteria cri) {
		return megmapper.getTotal(cri);
	}

	@Override
	public BoardDTO getDetail(Long boardnum) {
		return megmapper.findByNum(boardnum);
	}

	@Override
	public Long getLastNum(String userid) {
		return megmapper.getLastNum(userid);
	}

	@Override
	public ArrayList<String> getNewlyMessageList(List<MessageDTO> list) throws Exception {
		ArrayList<String> newly_Message = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		for(MessageDTO Message : list) {
			Date regdate = df.parse(Message.getSendDate());
			if(now.getTime() - regdate.getTime() < 1000*60*60*2) {
				newly_Message.add("O");
			}
			else {
				newly_Message.add("X");
			}
		}
		return newly_Message;
	}

	@Override
	public List<MessageDTO> getMyMessageList(Criteria cri, String userId) {
		return megmapper.getMyList(cri,userId);
	}
}













