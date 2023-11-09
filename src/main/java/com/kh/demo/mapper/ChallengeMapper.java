package com.kh.demo.mapper;

import com.kh.demo.domain.dto.MyChallengeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChallengeMapper {
    List<MyChallengeDTO> getIngMychall(String userid, String choicedate);
}
