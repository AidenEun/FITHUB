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
	int deleteReply(Long replyNum);
	int deleteByBoardnum(Long boardNum);

	//select
	Long getLastNum(String userId);
	int getTotal(Long boardNum);
	List<ReplyDTO> getList(Criteria cri, Long boardNum);
	int getRecentReply(Long boardNum);
}








