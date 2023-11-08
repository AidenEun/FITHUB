package com.kh.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    int getRecentReply(long boardNum);

    Integer getTotal(long boardNum);
}
