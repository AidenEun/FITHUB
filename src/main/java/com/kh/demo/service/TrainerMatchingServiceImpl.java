package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerMatchingBoardDTO;
import com.kh.demo.mapper.CalorieMapper;
import com.kh.demo.mapper.ReviewMapper;
import com.kh.demo.mapper.TrainerMapper;
import com.kh.demo.mapper.TrainerMatchingMapper;
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

    @Override
    public List<TrainerMatchingBoardDTO> getmatchingList(Criteria cri) {
        return tmmapper.getList(cri);
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

    public String getNickname(String trainerId){
        return tmapper.getNickname(trainerId);
    };

    public String getCareer(String trainerId){
        return tmapper.getCareer(trainerId);
    };


}
