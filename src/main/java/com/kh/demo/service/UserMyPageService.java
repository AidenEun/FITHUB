package com.kh.demo.service;

import com.kh.demo.domain.dto.DiaryDTO;
import com.kh.demo.domain.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserMyPageService {
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
}
