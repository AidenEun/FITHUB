package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerSignUpDTO;
import com.kh.demo.mapper.TrainerSignUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("TrainerSignUpServiceImpl")
public class TrainerSignUpServiceImpl implements TrainerSignUpService{
    @Autowired
    private TrainerSignUpMapper signUpMapper;

    @Override
    public List<TrainerSignUpDTO> getSignUpList(Criteria cri) {
        return signUpMapper.getSignUpList(cri);
    }
}
