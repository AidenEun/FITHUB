package com.kh.demo.mapper;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
	//insert
	int insertBoard(BoardDTO board);
	
	//update
	int updateBoard(BoardDTO board);
	int updateReadCount(Long board_num);
	
	//delete
	int deleteBoard(Long board_num);
	
	//select
	List<BoardDTO> getList(Criteria cri);
	Long getTotal(Criteria cri);
	Long getLastNum(String user_id);
	BoardDTO findByNum(Long board_num);
}



