package com.kh.demo.service;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.BookMarkDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ProductBoardDTO;
import com.kh.demo.mapper.BookMarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("BookMarkServiceImpl")
public class BookMarkServiceImpl implements BookMarkService{

	@Autowired
	private BookMarkMapper bookmarkmapper;



	@Override
	public boolean remove(String loginUser, Long boardnum) {
		BoardDTO board = bookmarkmapper.findByNum(boardnum);
		if(board.getUserId().equals(loginUser)) {

			return bookmarkmapper.deleteBoard(boardnum) == 1;
		}
		return false;
	}

	@Override
	public Long getTotal(Criteria cri, String userId) {
		return bookmarkmapper.getTotal(cri,userId);
	}

	@Override
	public BoardDTO getDetail(Long boardnum) {
		return bookmarkmapper.findByNum(boardnum);
	}

	@Override
	public Long getLastNum(String userid) {
		return bookmarkmapper.getLastNum(userid);
	}

	@Override
	public ArrayList<String> getNewlyList(List<BookMarkDTO> list) throws Exception {
		ArrayList<String> newly_Message = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		for(BookMarkDTO bookmark : list) {
			Date regdate = df.parse(bookmark.getRegdate());
			if(now.getTime() - regdate.getTime() < 1000*60*60*2) {
				newly_Message.add("O");
			}
			else {
				newly_Message.add("X");
			}
		}
		return newly_Message;
	}

}













