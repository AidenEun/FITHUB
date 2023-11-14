package com.kh.demo.service;

import com.kh.demo.domain.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {
    TrainerDTO getDetail(String userid);

    List<TrainerDTO> getTrainerTopList();

    List<TrainerDTO> getTrainerTop5List();

}
