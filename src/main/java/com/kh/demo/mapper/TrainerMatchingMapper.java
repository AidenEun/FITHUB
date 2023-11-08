package com.kh.demo.mapper;

import com.kh.demo.domain.dto.TrainerMatchingBoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrainerMatchingMapper {
    int insertBoard(TrainerMatchingBoardDTO board);
}
