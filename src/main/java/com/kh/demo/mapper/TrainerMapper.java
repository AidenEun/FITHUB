package com.kh.demo.mapper;


import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerDTO;
import com.kh.demo.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrainerMapper {

    TrainerDTO findById(String userid);

    int updateUser(TrainerDTO user);
}
