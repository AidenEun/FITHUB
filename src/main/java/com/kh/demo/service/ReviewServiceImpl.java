package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ReviewServiceImpl")
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewMapper rvmapper;


    public String getUtmatchingNum(Long boardNum) {
        return rvmapper.getUtmatchingNum(boardNum);
    }

    public boolean regist(ReviewDTO review) {
        return rvmapper.insertReview(review) == 1;
    }


    public boolean modify(ReviewDTO review) {
        return rvmapper.updateReview(review) == 1;
    }


    public boolean remove(Long reviewNum) {
        return rvmapper.deleteReview(reviewNum) == 1;
    }


    public ReviewPageDTO getList(Criteria cri, Long boardNum) {
        return new ReviewPageDTO(rvmapper.getTotal(boardNum), rvmapper.getList(cri, boardNum));
    }


    public Long getLastNum(String userId) {
        return rvmapper.getLastNum(userId);
    }
}

