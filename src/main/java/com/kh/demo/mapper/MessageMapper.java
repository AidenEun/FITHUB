package com.kh.demo.mapper;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.MessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {


	//delete
	int deleteBoard(Long board_num);
	
	//select
	/*List<BoardDTO> getMyList(@Param("cri") Criteria cri,@Param("userId") String userId);*/
	List<MessageDTO> getMyList(Criteria cri, String userId);

	Long getTotal(Criteria cri);
	Long getLastNum(String user_id);
	BoardDTO findByNum(Long board_num);
}



