package com.kh.demo.mapper;

import com.kh.demo.domain.dto.Criteria;

import com.kh.demo.domain.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //insert
    int insertReview(ReviewDTO review);

    //update
    int updateReview(ReviewDTO review);

    //delete
    int deleteReview(Long reviewNum);
    int deleteByBoardNum(Long boardNum);

    //select
    Long getLastNum(String userId);
    int getTotal(Long boardNum);
    List<ReviewDTO> getList(Criteria cri, Long boardNum);
    int getRecentReview(Long boardNum);

    String getUtmatchingNum(Long boardNum);

    Double getStarRatingAv(Long boardNum);

    Double getStarAv(Long boardNum);
}