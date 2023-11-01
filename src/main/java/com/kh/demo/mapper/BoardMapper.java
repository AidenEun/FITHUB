package com.kh.demo.mapper;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
	//insert
	int insertBoard(BoardDTO board);
	
	//update
	int updateBoard(BoardDTO board);
	int updateReadCount(Long boardNum);
	
	//delete
	int deleteBoard(Long boardNum);
	
	//select
	List<BoardDTO> getList(Criteria cri);
	/*List<BoardDTO> getMyList(@Param("cri") Criteria cri,@Param("userId") String userId);*/
	List<BoardDTO> getMyList(Criteria cri, String userId);

	Long getTotal(Criteria cri);
	Long getLastNum(String userId);
	BoardDTO findByNum(Long boardNum);
}



