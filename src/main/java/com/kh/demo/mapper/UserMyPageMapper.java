package com.kh.demo.mapper;

import com.kh.demo.domain.dto.DiaryDTO;
import org.apache.ibatis.annotations.Mapper;

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
    DiaryDTO getDiaryDetail(Long diary_num);

    DiaryDTO checkList(String choicedate);
}
