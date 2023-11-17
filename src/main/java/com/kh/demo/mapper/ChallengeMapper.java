package com.kh.demo.mapper;

import com.kh.demo.domain.dto.ChallNoticeBoardDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.MyChallengeDTO;
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
    ChallNoticeBoardDTO getFindchall(String challNum);

    List<ChallNoticeBoardDTO> getChallSearchList(String keyword);

    int insertChallNotice(ChallNoticeBoardDTO chall);


    List<ChallNoticeBoardDTO> getChallengeAllAll(Criteria noticeCri);

    List<ChallNoticeBoardDTO> getChallengeAllTerm(Criteria noticeCri, String challTerm);

    List<ChallNoticeBoardDTO> getChallengeCategoryAll(Criteria noticeCri, String challCategory);

    List<ChallNoticeBoardDTO> getChallengeCategoryTerm(Criteria noticeCri, String challCategory, String challTerm);

    Long getChallengeAllAllTotal(Criteria noticeCri);

    Long getChallengeAllTermTotal(Criteria noticeCri, String challTerm);

    Long getChallengeCategoryAllTotal(Criteria noticeCri, String challCategory);

    Long getChallengeCategoryTermTotal(Criteria noticeCri, String challCategory, String challTerm);

    ChallNoticeBoardDTO getChallNoticeDetail(Long challNum);

    void insertMyChall(long challNum, String id);

    void deleteChallNotice(long challNum);
}
