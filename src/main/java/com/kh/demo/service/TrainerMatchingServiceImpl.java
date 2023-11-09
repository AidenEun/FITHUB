package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ProfileDTO;
import com.kh.demo.domain.dto.TrainerDTO;
import com.kh.demo.domain.dto.TrainerMatchingBoardDTO;
import com.kh.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("TrainerMatchingServiceImpl")
public class TrainerMatchingServiceImpl implements TrainerMatchingService {


    @Autowired
    private TrainerMatchingMapper tmmapper;
    @Autowired
    private TrainerMapper tmapper;
    @Autowired
    private ReviewMapper rvmapper;
    @Autowired
    private ProfileMapper pfmapper;


    @Override
    public List<TrainerMatchingBoardDTO> getmatchingList(Criteria cri) {
        List<TrainerMatchingBoardDTO> list = tmmapper.getList(cri);

        for (TrainerMatchingBoardDTO board : list) {
            // 각각의 board에 대한 trainer 정보를 가져와서 설정
            ProfileDTO profileInfo = pfmapper.getProfileInfo(board.getTrainerId());
            TrainerDTO trainerInfo = tmapper.getTrainerInfo(board.getTrainerId());
            board.setTrainerInfo(trainerInfo);
            board.setProfileInfo(profileInfo);



        }

        return list;
    }


    public boolean regist(TrainerMatchingBoardDTO board){
        int row = tmmapper.insertBoard(board);
        if(row != 1) {
            return false;
        }
        return true;
    };




/*    @Override
    public ArrayList<Integer> getReviewCntList(List<TrainerMatchingBoardDTO> list) {
        ArrayList<Integer> review_cnt_list = new ArrayList<>();
        for(TrainerMatchingBoardDTO board : list) {
            review_cnt_list.add(rvmapper.getTotal(board.getBoardNum()));
        }
        return review_cnt_list;
    }*/






    public Long getLastNum(String trainerId){
        return tmmapper.getLastNum(trainerId);
    }



    ;


}
