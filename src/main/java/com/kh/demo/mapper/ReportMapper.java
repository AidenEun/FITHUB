package com.kh.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
    int insertReport(Object userId, String boardCategory, Long boardNum, String reportContent, String reportedUserId);

}
