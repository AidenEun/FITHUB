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

    Long getMessageLastNum(String trainerId);

    List<MessageDTO> getMyMessage(Criteria cri, String trainerId);


    //보드
    Long getBoardTotal(Criteria cri, String trainerId);

    BoardDTO findBoardByNum(Long boardnum);

    Long getBoardLastNum(String trainerId);

    List<BoardDTO> getMyBoard(Criteria cri, String trainerId);

    //북마크
    Long getBookmarkTotal(Criteria cri, String trainerId);

    Long getBookmarkLastNum(String trainerId);

    List<BoardDTO> getMyBookmark(Criteria cri, String trainerId);

    List<ProductBoardDTO> getMyBookmarkProduct(Criteria cri, String trainerId);


    //내구독
    List<UserDTO> getMyScribe(Criteria cri, String trainerId);

    Long getScribeTotal(Criteria cri, String trainerId);


    //내챌린지
    Long getChallengeTotal(Criteria cri, String trainerId);

    List<ChallNoticeBoardDTO> getMyChallenge(Criteria cri, String trainerId);
}
