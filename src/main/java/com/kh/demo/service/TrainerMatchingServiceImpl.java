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
    TrainerMatchingMapper tmmapper;

    @Autowired
    private TrainerMapper tmapper;

    @Autowired
    ReviewMapper rvmapper;

    @Override
    public List<TrainerMatchingBoardDTO> getmatchingList() {
        return tmmapper.getList();
    }

    public boolean regist(TrainerMatchingBoardDTO board){
        int row = tmmapper.insertBoard(board);
        if(row != 1) {
            return false;
        }
        return true;
    };

    @Override
    public Long getTotal(Criteria cri) {
        return tmmapper.getTotal(cri);
    }

    @Override
    public ArrayList<String> getRecentReplyList(List<TrainerMatchingBoardDTO> list) {
        ArrayList<String> recent_reply = new ArrayList<>();
        for(TrainerMatchingBoardDTO board : list) {
            if(rvmapper.getRecentReply(board.getBoardNum()) >= 5) {
                recent_reply.add("O");
            }
            else {
                recent_reply.add("X");
            }
        }
        return recent_reply;
    }

    @Override
    public ArrayList<Integer> getReplyCntList(List<TrainerMatchingBoardDTO> list) {
        ArrayList<Integer> reply_cnt_list = new ArrayList<>();
        for(TrainerMatchingBoardDTO board : list) {
            reply_cnt_list.add(rvmapper.getTotal(board.getBoardNum()));
        }
        return reply_cnt_list;
    }

    @Override
    public ArrayList<String> getNewlyBoardList(List<TrainerMatchingBoardDTO> list) throws Exception {
        ArrayList<String> newly_board = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for(TrainerMatchingBoardDTO board : list) {
            Date regdate = df.parse(board.getRegdate());
            if(now.getTime() - regdate.getTime() < 1000*60*60*2) {
                newly_board.add("O");
            }
            else {
                newly_board.add("X");
            }
        }
        return newly_board;
    }





    public Long getLastNum(String trainerId){
        return tmmapper.getLastNum(trainerId);
    };

    public String getNickname(String trainerId){
        return tmapper.getNickname(trainerId);
    };

    public String getCareer(String trainerId){
        return tmapper.getCareer(trainerId);
    };


}
