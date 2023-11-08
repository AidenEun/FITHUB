package com.kh.demo.service;

import com.kh.demo.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ReportServiceImpl")
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public boolean reportRegist(Object userId, String boardCategory, Long boardNum, String reportContent, String reportedUser){
        int row = reportMapper.insertReport(userId, boardCategory, boardNum, reportContent, reportedUser);

        return row == 1;
    }
}
