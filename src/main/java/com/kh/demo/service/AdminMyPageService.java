package com.kh.demo.service;

import com.kh.demo.domain.dto.*;

import java.util.List;

public interface AdminMyPageService {
//    Report
    List<ReportDTO> getReportList(Criteria cri);

    List<ReportDTO> getReportListByUser(Criteria cri);

    List<ReportDTO> getReportListByTrainer(Criteria cri);

    Long getReportTotal(Criteria cri);

    Long getReportTotalByUser(Criteria cri);

    Long getReportTotalByTrainer(Criteria cri);

//    SignUp
    List<TrainerSignUpDTO> getSignUpList(Criteria cri);

    Long getSignUpTotal(Criteria cri);

//    AdminBoard
    List<BoardDTO> getBoardList(Criteria cri);

    Long getBoardTotal(Criteria cri);

    List<BoardDTO> getAdminExerBoard(Criteria cri);

    List<BoardDTO> getAdminNewsBoard(Criteria cri);

    List<BoardDTO> getAdminRecipeBoard(Criteria cri);

    List<BoardDTO> getAdminTipBoard(Criteria cri);

    Long getExerBoardTotal(Criteria cri);

    Long getNewsBoardTotal(Criteria cri);

    Long getTipBoardTotal(Criteria cri);

    Long getRecipeBoardTotal(Criteria cri);


    TrainerDTO getTrainer(String keyword);

    UserDTO getUser(String keyword);

    List<UserDTO> getUserList(Criteria cri);

    List<TrainerDTO> getTrainerList(Criteria cri);

    Long getUserTotal(Criteria cri);

    Long getTrainerTotal(Criteria cri);

    Object getUserById(String userId);

    List<MessageDTO> getMessageList(Criteria cri);

    Long getMessageTotal(Criteria cri);

    List<MessageDTO> getMessageByUser(Criteria cri);

    List<MessageDTO> getMessageByTrainer(Criteria cri);

    Long getMessageTotalByUser(Criteria cri);

    Long getMessageTotalByTrainer(Criteria cri);

    List<ReportDTO> getDoneReportList(Criteria cri);

    Long getDoneReportTotal(Criteria cri);

    ReportDTO getReportDTO(Long reportNum);


}
