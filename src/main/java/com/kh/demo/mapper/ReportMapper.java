package com.kh.demo.mapper;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<ReportDTO> getReportList(Criteria cri);

    List<ReportDTO> getReportListByUser(Criteria cri);

    List<ReportDTO> getReportListByTrainer(Criteria cri);
}
