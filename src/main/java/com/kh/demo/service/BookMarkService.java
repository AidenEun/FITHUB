package com.kh.demo.service;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.BookMarkDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ProductBoardDTO;

import java.util.ArrayList;
import java.util.List;

public interface BookMarkService {
	//insert

	
	//delete
	public boolean remove(String loginUser, Long boardnum);

	//select
	Long getTotal(Criteria cri, String userId);
	BoardDTO getDetail(Long boardnum);
	Long getLastNum(String userid);
	ArrayList<String> getNewlyList(List<BookMarkDTO> list) throws Exception;

	List<BookMarkDTO> getMyList(Criteria cri, String userId);

	List<BoardDTO> getMyBookmark(Criteria cri, String userId);

	List<ProductBoardDTO> getMyBookmarkProduct(Criteria cri, String userId);
}








