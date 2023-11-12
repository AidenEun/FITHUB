package com.kh.demo.mapper;

import com.kh.demo.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChallengeMapper {


    
    /*재우*/
    //insert
    int insertBoard(ChallCertBoardDTO chall);

    //update
    int updateBoard(ChallCertBoardDTO chall);
    int updateReadCount(Long boardNum);

    //delete
    int deleteBoard(Long boardNum);

    //select
    List<ChallCertBoardDTO> getList(Criteria cri);
    Long getTotal(Criteria cri);
    Long getLastNum(String userId);
    ChallCertBoardDTO findByNum(Long boardNum);

    List<MyChallengeDTO> getIngMychall(String userid, String choicedate);

    ChallNoticeBoardDTO getChallenge(String userId, String mychallNum);
    List<MyChallengeDTO> getFindchall(String challNum);
}
