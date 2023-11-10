package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface TrainerMyPageService {
    //일기 등록 : insert
    public boolean registDiary (DiaryDTO diary, MultipartFile[] files) throws Exception;


    //일기 수정 : 기록날짜 제외한 모든 수정내용 update
    public boolean modifyDiary(DiaryDTO diary, MultipartFile[] files);

    //일기 삭제 : delete 다이어리 view에서 삭제 진행
    public boolean removeDiary(Long diaryNum);

    //select
    //일기 상세보기(view)
    public DiaryDTO getDiaryDetail(String choicedate);

    //달력에 존재하는 일기 조회하기
    public List<DiaryDTO> getDiaryList(String userid);

    //일정 여부 확인
    public DiaryDTO checkList(String choicedate,String loginUser);



    /*재우*/
    //메세지
    Long getMessageTotal(Criteria cri, String trainer, String message);
    Long getMessageLastNum(String userid);
    ArrayList<String> getMessageNewlyList(List<MessageDTO> list) throws Exception;

    List<MessageDTO> getMessageMyList(Criteria cri, String trainerId, String message);

    //보드
    Long getBoardTotal(Criteria cri, String trainerId);
    BoardDTO getBoardDetail(Long boardnum);
    Long getBoardLastNum(String userid);
    ArrayList<String> getBoardNewlyList(List<BoardDTO> list) throws Exception;
    ArrayList<Integer> getBoardReplyCntList(List<BoardDTO> list);
    ArrayList<String> getBoardRecentReplyList(List<BoardDTO> list);

    List<BoardDTO> getBoardMyList(Criteria cri, String trainerId);

    //북마크
    Long getBookmarkTotal(Criteria cri, String userId);
    Long getBookmarkProductTotal(Criteria cri, String trainerId);

    Long getBookmarkLastNum(String userid);
    ArrayList<String> getBookmarkNewlyList(List<BookMarkDTO> list) throws Exception;
    List<BoardDTO> getMyBookmark(Criteria cri, String trainerId);
    List<ProductBoardDTO> getMyBookmarkProduct(Criteria cri, String userId);

    //내정보수정
    TrainerDTO getUserDetail(String userid);
    boolean user_modify(TrainerDTO trainerdto);

    //내구독
    List<UserDTO> getMyScribe(Criteria cri, String userId);

    Long getScribeTotal(Criteria cri, String userId);

    //내 챌린지
    Long getChallengeTotal(Criteria cri, String trainerId, String challCategory, String challTerm);

    List<ChallNoticeBoardDTO> getMyChallenge(Criteria cri, String trainerId, String challCategory, String challTerm);
    //트레이너 프로필
    List<ProfileDTO> getProFileList(String trainerId);
    List<ProfileDTO> getFileList(String trainerId);

    ResponseEntity<Resource> getThumbnailResource(String sysName) throws Exception;

    boolean trainer_modify(TrainerDTO trainer, MultipartFile[] files, String updateCnt) throws IOException;
}
