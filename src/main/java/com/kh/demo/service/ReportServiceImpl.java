package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReportDTO;
import com.kh.demo.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ReportServiceImpl")
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<ReportDTO> getReportList(Criteria cri) {
        return reportMapper.getReportList(cri);
    }
}
