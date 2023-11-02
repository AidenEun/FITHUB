package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.AdminMyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ReportServiceImpl")
public class AdminMyPageServiceImpl implements AdminMyPageService {
    @Autowired
    private AdminMyPageMapper adminMyPageMapper;

//    Report
    @Override
    public List<ReportDTO> getReportList(Criteria cri) {
        return adminMyPageMapper.getReportList(cri);
    }

    @Override
    public List<ReportDTO> getReportListByUser(Criteria cri) {
        return adminMyPageMapper.getReportListByUser(cri);
    }

    @Override
    public List<ReportDTO> getReportListByTrainer(Criteria cri) {
        return adminMyPageMapper.getReportListByTrainer(cri);
    }

    @Override
    public Long getReportTotal(Criteria cri) {
        return adminMyPageMapper.getReportTotal(cri);
    }

    @Override
    public Long getReportTotalByUser(Criteria cri) {
        return adminMyPageMapper.getReportTotalByUser(cri);
    }

    @Override
    public Long getReportTotalByTrainer(Criteria cri) {
        return adminMyPageMapper.getReportTotalByTrainer(cri);
    }

//    SignUp
    @Override
    public List<TrainerSignUpDTO> getSignUpList(Criteria cri) {
        return adminMyPageMapper.getSignUpList(cri);
    }

    @Override
    public Long getSignUpTotal(Criteria cri) {
        return adminMyPageMapper.getSignUpTotal(cri);
    }

//    AdminBoard
    @Override
    public List<BoardDTO> getBoardList(Criteria cri) {
        return adminMyPageMapper.getBoardList(cri);
    }

    @Override
    public Long getBoardTotal(Criteria cri) {
        return adminMyPageMapper.getBoardTotal(cri);
    }

    @Override
    public List<BoardDTO> getAdminExerBoard(Criteria cri) {
        return adminMyPageMapper.getAdminExerBoardList(cri);
    }

    @Override
    public List<BoardDTO> getAdminNewsBoard(Criteria cri) {
        return adminMyPageMapper.getAdminNewsBoardList(cri);
    }

    @Override
    public List<BoardDTO> getAdminRecipeBoard(Criteria cri) {
        return adminMyPageMapper.getAdminRecipeBoardList(cri);
    }

    @Override
    public List<BoardDTO> getAdminTipBoard(Criteria cri) {
        return adminMyPageMapper.getAdminTipBoardList(cri);
    }

    @Override
    public Long getExerBoardTotal(Criteria cri) {
        return adminMyPageMapper.getExerBoardTotal(cri);
    }

    @Override
    public Long getNewsBoardTotal(Criteria cri) {
        return adminMyPageMapper.getNewsBoardTotal(cri);
    }

    @Override
    public Long getTipBoardTotal(Criteria cri) {
        return adminMyPageMapper.getTipBoardTotal(cri);
    }

    @Override
    public Long getRecipeBoardTotal(Criteria cri) {
        return adminMyPageMapper.getRecipeBoardTotal(cri);
    }

//    SearchUser
    @Override
    public TrainerDTO getTrainer(String keyword) {
        return adminMyPageMapper.getTrainer(keyword);
    }

    @Override
    public UserDTO getUser(String keyword) {
        return adminMyPageMapper.getUser(keyword);
    }

    @Override
    public List<UserDTO> getUserList(Criteria cri) {
        return adminMyPageMapper.getUserList(cri);
    }

    @Override
    public List<TrainerDTO> getTrainerList(Criteria cri) {
        return adminMyPageMapper.getTrainerList(cri);
    }

    @Override
    public Long getUserTotal(Criteria cri) {
        return adminMyPageMapper.getUserTotal(cri);
    }

    @Override
    public Long getTrainerTotal(Criteria cri) {
        return adminMyPageMapper.getTrainerTotal(cri);
    }

}
