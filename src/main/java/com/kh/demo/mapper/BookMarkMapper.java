package com.kh.demo.mapper;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.BookMarkDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ProductBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMarkMapper {


	//delete
	int deleteBoard(Long board_num);
	
	Long getTotal(Criteria cri, String userId);
	Long getLastNum(String user_id);
	BoardDTO findByNum(Long board_num);

}



