package com.kh.demo.service;

import com.kh.demo.domain.dto.MyChallengeDTO;
import com.kh.demo.mapper.ChallengeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ChallengeServiceImpl")
public class ChallengeServiceImpl implements ChallengeService{
    
    @Autowired
    private ChallengeMapper challMapper;
    @Override
    public List<MyChallengeDTO> findMychall(String userid,String choicedate) {
        return challMapper.getIngMychall(userid,choicedate);
    }
}
