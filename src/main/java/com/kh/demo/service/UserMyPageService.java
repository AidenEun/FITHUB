package com.kh.demo.service;

import com.kh.demo.domain.dto.DiaryDTO;
import com.kh.demo.domain.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserMyPageService {
    //insert
    boolean regist(DiaryDTO diary, MultipartFile files,String loginUser) throws Exception;

    //update
    public boolean modify(DiaryDTO diary, MultipartFile[] files, String regdate);
    public boolean remove(String loginUser, Long diary_num);
    //select
    List<DiaryDTO> getDiaryList(String loginUser);
    DiaryDTO getDiaryDetail(Long diary_num);

    DiaryDTO findByNum(Long diary_num);
    List<FileDTO> getFileList(Long diary_num);
}
