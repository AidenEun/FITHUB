package com.kh.demo.mapper;


import com.kh.demo.domain.dto.TrainerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrainerMapper {

    TrainerDTO findById(String userid);

    int updateUser(TrainerDTO user);
    

    String getNickname(String trainerId);

    String getCareer(String trainerId);
}
