package com.kh.demo.mapper;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerSignUpDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainerSignUpMapper {
    List<TrainerSignUpDTO> getSignUpList(Criteria cri);
}
