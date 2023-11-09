package com.kh.demo.service;

import com.kh.demo.domain.dto.MyChallengeDTO;

import java.util.List;

public interface ChallengeService {
    List<MyChallengeDTO> findMychall(String userid,String choicedate);
}
