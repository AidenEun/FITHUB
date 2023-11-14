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
	boolean regist(BoardDTO board, MultipartFile[] files, MultipartFile[] files2) throws Exception;

	//update
	public boolean modify(BoardDTO board, MultipartFile[] files, MultipartFile[] files2, String updateCnt, String boardCategory) throws Exception;

	public void updateReadCount(Long boardnum);

	//delete
	public boolean remove(String loginUser, Long boardnum, String boardCategory);

	//select
	Long getTotal(Criteria cri);

	List<BoardDTO> getBoardList(Criteria cri);

	List<BoardDTO> getNewsList(Criteria cri);

	List<BoardDTO> getExerList(Criteria cri);

	List<BoardDTO> getFoodList(Criteria cri);

	List<BoardDTO> getTipList(Criteria cri);

	BoardDTO getDetail(Long boardnum);

	Long getLastNum(String userid);

	ArrayList<String> getNewlyBoardList(List<BoardDTO> list) throws Exception;

	ArrayList<Integer> getReplyCntList(List<BoardDTO> list);

	ArrayList<String> getRecentReplyList(List<BoardDTO> list);

	List<FileDTO> getFileList(Long boardnum, String boardCategory);

	ResponseEntity<Resource> getThumbnailResource(String sysName) throws Exception;

	ResponseEntity<Object> downloadFile(String systemname, String orgname) throws Exception;


	List<BoardDTO> getAllBoardList(String keyword);

	Long getAllsearchCnt(String keyword);

	List<BoardDTO> getNewsSearchList(String keyword);

	List<BoardDTO> getExerSearchList(String keyword);

	List<BoardDTO> getFoodSearchList(String keyword);

	List<BoardDTO> getTipSearchList(String keyword);

	List<BoardDTO> getCommuSearchList(String keyword);


	BoardDTO getNewsTop1();

	BoardDTO getExerTop1();

	BoardDTO getFoodTop1();

	List<BoardDTO> getBoardTop5List();

	List<BoardDTO> getinfoSearchList(String keyword);

//    int getNewsSearchCnt(String keyword);
}








