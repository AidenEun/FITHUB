package com.kh.demo.mapper;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.ChallNoticeBoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.MyChallengeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChallengeMapper {


    
    /*재우*/
    //insert
    int insertBoard(BoardDTO board);

    //update
    int updateBoard(BoardDTO board);
    int updateReadCount(Long boardNum);

    //delete
    int deleteBoard(Long boardNum);

    //select
    List<BoardDTO> getList(Criteria cri);
    Long getTotal(Criteria cri);
    Long getLastNum(String userId);
    BoardDTO findByNum(Long boardNum);

    List<MyChallengeDTO> getIngMychall(String userid, String choicedate);

    List<MyChallengeDTO> getFindchall(String challNum);

    List<ChallNoticeBoardDTO> getChallSearchList(String keyword);
}
