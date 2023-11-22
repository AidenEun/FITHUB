package com.kh.demo.service;

import com.kh.demo.domain.dto.*;

public interface ReviewService {
    boolean regist(ReviewDTO review);

    boolean modify(ReviewDTO review);

    boolean remove(Long reviewNum);

    ReviewPageDTO getList(Criteria cri, Long boardNum);

    Long getLastNum(String userId);

    String getUtmatchingNum(Long boardNum);

}



