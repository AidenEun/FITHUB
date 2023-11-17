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

	List<BoardDTO> getInfoNewsList(Criteria cri);

	List<BoardDTO> getInfoExerList(Criteria cri);

	List<BoardDTO> getInfoFoodList(Criteria cri);

	List<BoardDTO> getInfoTipList(Criteria cri);

	List<BoardDTO> getCommuFreeList(Criteria cri);
	List<BoardDTO> getCommuExerList(Criteria cri);

	List<BoardDTO> getCommuFoodList(Criteria cri);

	List<BoardDTO> getCommuGalleryList(Criteria cri);

	List<BoardDTO> getCommuQnaList(Criteria cri);

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


    List<BoardDTO> get12infoSearchList(Criteria cri);

	Long getinfoSearchCnt(Criteria cri);

	List<BoardDTO> get12TipSearchList(Criteria cri);

	Long getTipSearchTotalCnt(Criteria cri);

	List<BoardDTO> get12CommuSearchList(Criteria cri);

	Long getCommuTotalCnt(Criteria cri);
}








