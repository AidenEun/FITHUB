package com.kh.demo.service;


import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerDTO;
import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.mapper.TrainerMapper;
import com.kh.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Qualifier("TrainerServiceImpl")
public class TrainerServiceImpl implements TrainerService{

    @Autowired
    private TrainerMapper tmapper;

    @Override
    public TrainerDTO getDetail(String userid) {
        return tmapper.findById(userid);
    }

    @Override
    public List<TrainerDTO> getTrainerTopList() {
        return tmapper.getTrainerTop1();
    }

    @Override
    public List<TrainerDTO> getTrainerTop5List() {
        return tmapper.getTrainerTop5();
    }

    @Override
    public List<TrainerDTO> getTrainerTopNumList(int i) {
        return tmapper.getTrainerTopNumList(i);
    }

    @Override
    public List<TrainerDTO> getTrainerBoardTotalTop5List() {
        return tmapper.getTrainerBoardTotalTop5List();
    }

    @Override
    public List<TrainerDTO> getTrainerReplyTotalTop5List() {
        return tmapper.getTrainerReplyTotalTop5List();
    }
}
