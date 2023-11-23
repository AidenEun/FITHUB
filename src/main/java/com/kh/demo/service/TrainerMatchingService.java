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

    TrainerMatchingBoardDTO boardView(Long boardNum);

    void updateViewCount(Long boardNum);

    ProfileDTO getProfileInfo(String trainerId);

    List<ProfileDTO> getCareerInfo(String trainerId);

    TrainerDTO getTrainerInfo(String trainerId);

    Object getUserByNickname(String trainerNickname);

    TrainerMatchingBoardDTO getBoardBytrainerId(String trainerId);

    void saveMatching(UTMatchingDTO newMatching);

    UTMatchingDTO getutBytrainerId(String trainerId);

    List<TrainerMatchingBoardDTO> getMachingSearchList(String keyword);

    void saveMessage(MessageDTO newMessage);


    SubscribeDTO checkSubs(SubscribeDTO newSubscribe);

    String clickSubs(SubscribeDTO newSubscribe);

    List<TrainerMatchingBoardDTO> get12matchingSearchList(Criteria cri);

    Long getmatchingTotal(Criteria cri);

    boolean remove(String loginUser, Long boardNum);

    boolean modify(TrainerMatchingBoardDTO board);

    Long getTotal(Criteria cri);

    TrainerMatchingBoardDTO getDetail(Long boardNum);

    List<TrainerMatchingBoardDTO> getAllBoards(Long boardNum);

    List<TrainerDTO> getTrainerNickname(String trainerId);


    UTMatchingDTO uTtrainerCheck(String trainerId, String userId);

    Double getStarRatingAv(Long boardNum);


}
