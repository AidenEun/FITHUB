package com.kh.demo.service;

import com.kh.demo.domain.dto.LikeDTO;

public interface HeartService {

    public int insertHeart(LikeDTO heart);

    public void updateLikeCount(LikeDTO heart);

}
