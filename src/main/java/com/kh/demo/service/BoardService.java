package com.kh.demo.service;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.FileDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {
	//insert
	boolean regist(BoardDTO board, MultipartFile[] files) throws Exception;
	
	//update
	public boolean modify(BoardDTO board, MultipartFile[] files, String updateCnt) throws Exception;
	public void updateReadCount(Long boardnum);
	
	//delete
	public boolean remove(String loginUser, Long boardnum);
	
	//select
	Long getTotal(Criteria cri);
	List<BoardDTO> getBoardList(Criteria cri);
	BoardDTO getDetail(Long boardnum);
	Long getLastNum(String userid);
	ArrayList<String> getNewlyBoardList(List<BoardDTO> list) throws Exception;
	ArrayList<Integer> getReplyCntList(List<BoardDTO> list);
	ArrayList<String> getRecentReplyList(List<BoardDTO> list);
	List<FileDTO> getFileList(Long boardnum);

	ResponseEntity<Resource> getThumbnailResource(String systemname) throws Exception;

	ResponseEntity<Object> downloadFile(String systemname, String orgname) throws Exception;


	List<BoardDTO> getMyBoardList(Criteria cri, String userId);
}








