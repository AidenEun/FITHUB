package com.kh.demo.mapper;

import com.kh.demo.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMyPageMapper {
    //insert
    int insertDiary(DiaryDTO diary);

    //update
    int updateDiary(DiaryDTO diary);

    //delete
    int deleteDiary(Long diary_num);

    //select
    List<DiaryDTO> getDiaryList(String loginUser);

    DiaryDTO getDiaryDetail(String choicedate, String loginUser);

    DiaryDTO checkList(String choicedate, String loginUser);


    //재우
    //메세지
    Long getMessageTotal(Criteria cri, String userId);

    Long getMessageTotalSend(Criteria cri, String userId);

    Long getMessageTotalReceive(Criteria cri, String userId);

    Long getMessageLastNum(String userid);

    List<MessageDTO> getMyMessageAll(Criteria cri, String userId);

    List<MessageDTO> getMyMessageSend(Criteria cri, String userId);


    List<MessageDTO> getMyMessageReceive(Criteria cri, String userId);

    //나의 매칭리스트
    List<UTMatchingDTO> getMyMatchinglist(Criteria cri, String userId);

    int updateMatching(UTMatchingDTO utMatching);

    //보드
    Long getBoardTotal(Criteria cri, String userId);

    BoardDTO findBoardByNum(Long boardnum);

    Long getBoardLastNum(String userid);

    List<BoardDTO> getMyBoard(Criteria cri, String userId);


    //북마크
    Long getBookmarkTotal(Criteria cri, String userId);

    Long getBookmarkProductTotal(Criteria cri, String userId);

    Long getBookmarkLastNum(String userid);

    List<BoardDTO> getMyBookmark(Criteria cri, String userId);

    List<ProductBoardDTO> getMyBookmarkProduct(Criteria cri, String userId);


    //내구독
    List<TrainerDTO> getMyScribe(Criteria cri, String userId);

    Long getScribeTotal(Criteria cri, String userId);


    //내챌린지
    Long getChallengeAllAllTotal(Criteria cri, String userId);

    Long getChallengeAllTermTotal(Criteria cri, String userId, String challTerm);

    Long getChallengeCategoryAllTotal(Criteria cri, String userId, String challCategory);

    Long getChallengeCategoryTermTotal(Criteria cri, String userId, String challCategory, String challTerm);


    List<ChallNoticeBoardDTO> getMyChallengeAllAll(Criteria cri, String userId);

    List<ChallNoticeBoardDTO> getMyChallengeAllTerm(Criteria cri, String userId, String challTerm);

    List<ChallNoticeBoardDTO> getMyChallengeCategoryAll(Criteria cri, String userId, String challCategory);

    List<ChallNoticeBoardDTO> getMyChallengeCategoryTerm(Criteria cri, String userId, String challCategory, String challTerm);

    List<ChallNoticeBoardDTO> getSuccessMyChallenge(String userId);

    Long getSuccessMyChallengeTotal(String userId);


    //트레이너신청
    int insertApplytrainer(TrainerSignUpDTO user);

    Long getSignUpLastNum(String userId);

    int insertStamp(int sccChallNum, String userId, String diarydate);

    DiaryDTO getDiaryByNum(Long diaryNum);

    int removeStamp(String userId, String regdate);
  
    Long getMatchingTotal(Criteria cri, String userId);

    DiaryDTO getDiaryandUser(String todaydate, String userid);
}
