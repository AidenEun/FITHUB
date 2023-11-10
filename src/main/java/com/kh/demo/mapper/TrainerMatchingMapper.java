package com.kh.demo.mapper;

import com.kh.demo.domain.dto.TrainerMatchingBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import com.kh.demo.domain.dto.Criteria;

import java.util.List;

@Mapper
public interface TrainerMatchingMapper {
    int insertBoard(TrainerMatchingBoardDTO board);

    List<TrainerMatchingBoardDTO> getList(Criteria cri);


    Long getLastNum(String trainerId);

   void updateViewCount(Long boardNum);

    List<TrainerMatchingBoardDTO> boardView(Long boardNum);
}
