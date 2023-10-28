package com.kh.demo.mapper;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
	//insert
	int insertReply(ReplyDTO reply);
	
	//update
	int updateReply(ReplyDTO reply);
	
	//delete
	int deleteReply(Long reply_num);
	int deleteByBoardnum(Long board_num);
	
	//select
	Long getLastNum(String userid);
	int getTotal(Long board_num);
	List<ReplyDTO> getList(Criteria cri, Long board_num);
	int getRecentReply(Long board_num);
}








