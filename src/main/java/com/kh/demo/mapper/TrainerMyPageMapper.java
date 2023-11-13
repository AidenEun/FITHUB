package com.kh.demo.mapper;


import com.kh.demo.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainerMyPageMapper {
    //insert
    int insertDiary(DiaryDTO diary);

    //update
    int updateDiary(DiaryDTO diary);

    //delete
    int deleteDiary(Long diary_num);

    //select
    List<DiaryDTO> getDiaryList(String loginUser);
    DiaryDTO getDiaryDetail(Long diary_num);

    DiaryDTO checkList(String choicedate,String loginUser);


    //재우
    //메세지
    Long getMessageTotal(Criteria cri, String trainerId);
    Long getMessageTotalSend(Criteria cri, String trainerId);
    Long getMessageTotalReceive(Criteria cri, String trainerId);

    List<MessageDTO> getMyMessageAll(Criteria cri, String trainerId);
    List<MessageDTO> getMyMessageSend(Criteria cri, String trainerId);
    List<MessageDTO> getMyMessageReceive(Criteria cri, String trainerId);

    Long getMessageLastNum(String trainerId);

    List<MessageDTO> getMyMessage(Criteria cri, String trainerId);

    //나의 매칭리스트
    List<UTMatchingDTO> getMyMatchinglist(Criteria cri, String trainerId);

    Long getMatchingTotal(Criteria cri, String trainerId);

    int updateMatching(UTMatchingDTO utMatching, String trainerCheck);



    //보드
    Long getBoardTotal(Criteria cri, String trainerId);

    BoardDTO findBoardByNum(Long boardnum);

    Long getBoardLastNum(String trainerId);

    List<BoardDTO> getMyBoard(Criteria cri, String trainerId);

    //북마크
    Long getBookmarkTotal(Criteria cri, String trainerId);
    Long getBookmarkProductTotal(Criteria cri, String trainerId);

    Long getBookmarkLastNum(String trainerId);

    List<BoardDTO> getMyBookmark(Criteria cri, String trainerId);

    List<ProductBoardDTO> getMyBookmarkProduct(Criteria cri, String trainerId);


    //내구독
    List<UserDTO> getMyScribe(Criteria cri, String trainerId);

    Long getScribeTotal(Criteria cri, String trainerId);

    Long getTrainerScribeTotal(String trainerId);

    //내챌린지
    List<ChallNoticeBoardDTO> getMyChallengeAllAll(Criteria cri, String trainerId);
    List<ChallNoticeBoardDTO> getMyChallengeAllTerm(Criteria cri, String trainerId, String challTerm);
    List<ChallNoticeBoardDTO> getMyChallengeCategoryAll(Criteria cri, String trainerId, String challCategory);
    List<ChallNoticeBoardDTO> getMyChallengeCategoryTerm(Criteria cri, String trainerId, String challCategory, String challTerm);

    Long getChallengeAllAllTotal(Criteria cri, String trainerId);
    Long getChallengeAllTermTotal(Criteria cri, String trainerId, String challTerm);
    Long getChallengeCategoryAllTotal(Criteria cri, String trainerId, String challCategory);
    Long getChallengeCategoryTermTotal(Criteria cri, String trainerId, String challCategory, String challTerm);

    //내 프로필
    int updateTrainer(TrainerDTO trainer);

}
