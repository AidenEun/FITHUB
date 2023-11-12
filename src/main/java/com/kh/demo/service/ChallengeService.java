package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface ChallengeService {




    /*재우*/
    boolean regist(ChallCertBoardDTO chall, MultipartFile[] files) throws Exception;

    //update
    public boolean modify(ChallCertBoardDTO chall, MultipartFile[] files, String updateCnt) throws Exception;
    public void updateReadCount(Long boardnum);

    //delete
    public boolean remove(String loginUser, Long boardnum);

    //select
    Long getTotal(Criteria cri);
    List<ChallCertBoardDTO> getChallList(Criteria cri);
    ChallCertBoardDTO getDetail(Long boardnum);
    Long getLastNum(String userId);
    ArrayList<String> getNewlyBoardList(List<ChallCertBoardDTO> list) throws Exception;
    ArrayList<Integer> getReplyCntList(List<ChallCertBoardDTO> list);
    ArrayList<String> getRecentReplyList(List<ChallCertBoardDTO> list);
    List<FileDTO> getFileList(Long boardnum);

    ResponseEntity<Resource> getThumbnailResource(String systemname) throws Exception;

    ResponseEntity<Object> downloadFile(String systemname, String orgname) throws Exception;


    List<MyChallengeDTO> findMychall(String userid,String choicedate);

    /*나의 챌린지 번호로 검색*/
    ChallNoticeBoardDTO getChallenge(String userId, String mychallNum);

    List<MyChallengeDTO> findchall(String challNum);
}
