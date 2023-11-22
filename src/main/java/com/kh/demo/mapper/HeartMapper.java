package com.kh.demo.mapper;

import com.kh.demo.domain.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HeartMapper {

    LikeDTO likeCheck(Long boardNum, String userId);

    int insertHeart(LikeDTO heart);

    void deleteHeart(LikeDTO heart);


}
