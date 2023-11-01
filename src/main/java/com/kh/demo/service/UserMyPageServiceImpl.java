package com.kh.demo.service;

import com.kh.demo.domain.dto.DiaryDTO;
import com.kh.demo.domain.dto.FileDTO;
import com.kh.demo.mapper.UserMyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Qualifier("UserMyPageServiceImpl")
public class UserMyPageServiceImpl implements UserMyPageService{

    @Autowired
    private UserMyPageMapper umpmapper;

    @Override
    public boolean registDiary(DiaryDTO diary, MultipartFile[] files) throws Exception {
        return false;
    }

    @Override
    public boolean modifyDiary(DiaryDTO diary, MultipartFile[] files) {
        return false;
    }

    @Override
    public boolean removeDiary(Long diaryNum) {
        return false;
    }

    @Override
    public DiaryDTO getDiaryDetail(String choicedate) {
        return null;
    }

    @Override
    public List<DiaryDTO> getDiaryList(String userid) {
        return null;
    }

    @Override
    public DiaryDTO checkList(String choicedate) {
        DiaryDTO result = umpmapper.checkList(choicedate);
        //작성으로 아동
        if (result == null) {
            return result;
        }
        return null;

    }
}
