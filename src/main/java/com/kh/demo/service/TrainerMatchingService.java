package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerMatchingBoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface TrainerMatchingService {
    List<TrainerMatchingBoardDTO> getmatchingList(Criteria cri);

    boolean regist(TrainerMatchingBoardDTO board);

    Long getLastNum(String trainerId);

   /* ArrayList<Integer> getReviewCntList(List<TrainerMatchingBoardDTO> list);*/

    String getNickname(String trainerId);

    String getCareer(String trainerId);
}
