package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerSignUpDTO;

import java.util.List;

public interface TrainerSignUpService {
    List<TrainerSignUpDTO> getSignUpList(Criteria cri);
}
