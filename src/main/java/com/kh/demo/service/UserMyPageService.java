package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UserMyPageService {
    //일기 등록 : insert
    //파일첨부 가능(업데이트)
    public boolean registDiary (DiaryDTO diary, MultipartFile[] files) throws Exception;

    //파일첨부 없음.(1차)
    int registDiary(DiaryDTO diary);

    //일기 수정 : 기록날짜 제외한 모든 수정내용 update
    public boolean modifyDiary(DiaryDTO diary, MultipartFile[] files);

    //일기 삭제 : delete 다이어리 view에서 삭제 진행
    public boolean removeDiary(Long diaryNum);

    //select
    //일기 상세보기(view)
    DiaryDTO getDiaryDetail(String choicedate, String loginUser);

    //달력에 존재하는 일기 조회하기
    public List<DiaryDTO> getDiaryList(String userid);

    //일정 여부 확인
    public DiaryDTO checkList(String choicedate,String loginUser);

    boolean addStemp(int sccChallNum, HashMap<String, String> diaryInfo);

    boolean modifyDiary(DiaryDTO diary);



    /*재우*/
    //메세지
    Long getMessageTotal(Criteria cri, String userId, String message);
    Long getMessageLastNum(String userid);
    ArrayList<String> getMessageNewlyList(List<MessageDTO> list) throws Exception;

    List<MessageDTO> getMessageMyList(Criteria cri, String userId, String message);


    //보드
    Long getBoardTotal(Criteria cri, String userId);
    BoardDTO getBoardDetail(Long boardnum);
    Long getBoardLastNum(String userid);
    ArrayList<String> getBoardNewlyList(List<BoardDTO> list) throws Exception;
    ArrayList<Integer> getBoardReplyCntList(List<BoardDTO> list);
    ArrayList<String> getBoardRecentReplyList(List<BoardDTO> list);

    List<BoardDTO> getBoardMyList(Criteria cri, String userId);


    //북마크
    Long getBookmarkTotal(Criteria cri, String userId);
    Long getBookmarkLastNum(String userid);
    ArrayList<String> getBookmarkNewlyList(List<BookMarkDTO> list) throws Exception;
    List<BoardDTO> getMyBookmark(Criteria cri, String userId);
    List<ProductBoardDTO> getMyBookmarkProduct(Criteria cri, String userId);

    Long getBookmarkProductTotal(Criteria cri, String userId);


    //내정보수정
    UserDTO getUserDetail(String userid);
    boolean user_modify(UserDTO user);

    //내구독
    List<TrainerDTO> getMyScribe(Criteria cri, String userId);

    Long getScribeTotal(Criteria cri, String userId);

    //내 챌린지
    List<ChallNoticeBoardDTO> getMyChallenge(Criteria cri, String userId, String challCategory, String challTerm);

    Long getChallengeTotal(Criteria cri, String userId, String challCategory, String challTerm);


    //트레이너 전환 신청
    boolean insertApplytrainer(TrainerSignUpDTO user,MultipartFile[] files) throws Exception;

    //나의 매칭리스트
    List<UTMatchingDTO> getMyMatchinglist(Criteria cri, String userId);

    ArrayList<String> getMatchingNewlyList(List<UTMatchingDTO> list) throws Exception;

    Long getMatchingTotal(Criteria cri, String userId);

    boolean updateMatching(UTMatchingDTO utMatching);
}
