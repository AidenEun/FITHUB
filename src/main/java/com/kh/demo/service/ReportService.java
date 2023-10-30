package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    List<ReportDTO> getReportList(Criteria cri);
}
