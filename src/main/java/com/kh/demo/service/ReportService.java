package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    List<ReportDTO> getReportList(Criteria cri);

    List<ReportDTO> getReportListByUser(Criteria cri);

    List<ReportDTO> getReportListByTrainer(Criteria cri);

    Long getTotal(Criteria cri);

    Long getTotalByUser(Criteria cri);

    Long getTotalByTrainer(Criteria cri);
}
