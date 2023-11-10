package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface TrainerMatchingService {
    List<TrainerMatchingBoardDTO> getmatchingList(Criteria cri);

    boolean regist(TrainerMatchingBoardDTO board);

    Long getLastNum(String trainerId);

   /* ArrayList<Integer> getReviewCntList(List<TrainerMatchingBoardDTO> list);*/

    ResponseEntity<Resource> getThumbnailResource(String sysName) throws Exception;

    List<TrainerMatchingBoardDTO> boardView(Long boardNum);

    void updateViewCount(Long boardNum);

    ProfileDTO getProfileInfo(String trainerId);

    ProfileDTO getCareerInfo(String trainerId);

    TrainerDTO getTrainerInfo(String trainerId);
}
