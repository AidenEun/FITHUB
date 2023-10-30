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
    public boolean regist(DiaryDTO diary, MultipartFile files, String loginUser) throws Exception {
        return false;
    }

    @Override
    public boolean modify(DiaryDTO diary, MultipartFile[] files, String regdate) {
        return false;
    }

    @Override
    public boolean remove(String loginUser, Long diary_num) {
        return false;
    }

    @Override
    public List<DiaryDTO> getDiaryList(String loginUser) {
        return umpmapper.getDiaryList(loginUser);
    }

    @Override
    public DiaryDTO getDiaryDetail(Long diary_num) {
        return null;
    }

    @Override
    public DiaryDTO findByNum(Long diary_num) {
        return null;
    }

    @Override
    public List<FileDTO> getFileList(Long diary_num) {
        return null;
    }
}
