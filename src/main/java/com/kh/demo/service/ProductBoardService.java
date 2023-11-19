package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.FileDTO;
import com.kh.demo.domain.dto.ProductBoardDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface ProductBoardService {
	//insert
	boolean regist(ProductBoardDTO pboard, MultipartFile[] files) throws Exception;

	//update
	public boolean modify(ProductBoardDTO pboard, MultipartFile[] files, String updateCnt, String category) throws Exception;

	public void updateReadCount(Long boardNum);

	//delete
	public boolean remove(String loginUser, Long boardNum, String category);

	//select
	Long getTotal(Criteria cri);

	List<ProductBoardDTO> getBoardList(Criteria cri);

	ProductBoardDTO getDetail(Long boardNum);

	Long getLastNum(String userid);

	ArrayList<String> getNewlyBoardList(List<ProductBoardDTO> list) throws Exception;

	ArrayList<Integer> getReplyCntList(List<ProductBoardDTO> list);

	ArrayList<String> getRecentReplyList(List<ProductBoardDTO> list);

	List<FileDTO> getFileList(Long boardNum, String category);

	ResponseEntity<Resource> getThumbnailResource(String sysName) throws Exception;

	ResponseEntity<Object> downloadFile(String systemname, String orgname) throws Exception;


	List<ProductBoardDTO> getProdFoodList(Criteria cri);

	List<ProductBoardDTO> getProdExerList(Criteria cri);
}








