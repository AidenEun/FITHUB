package com.kh.demo.service;

public interface ReportService {
    boolean reportRegist(Object userId, String boardCategory, Long boardNum, String reportContent, String reportedUserId);

}
