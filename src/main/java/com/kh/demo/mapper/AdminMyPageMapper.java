package com.kh.demo.mapper;

import com.kh.demo.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMyPageMapper {
//    Report
    List<ReportDTO> getReportList(Criteria cri);

    List<ReportDTO> getReportListByUser(Criteria cri);

    List<ReportDTO> getReportListByTrainer(Criteria cri);

    Long getReportTotal(Criteria cri);

    Long getReportTotalByUser(Criteria cri);

    Long getReportTotalByTrainer(Criteria cri);

    boolean getUserByIdBoolean(String userId);

    boolean getTrainerByIdBoolean(String userId);

    UserDTO getUserById(String userId);

    TrainerDTO getTrainerById(String userId);

//    SignUp
    List<TrainerSignUpDTO> getSignUpList(Criteria cri);

    Long getSignUpTotal(Criteria cri);

//    AdminBoard
    List<BoardDTO> getBoardList(Criteria cri);

    List<BoardDTO> getAdminExerBoardList(Criteria cri);

    List<BoardDTO> getAdminNewsBoardList(Criteria cri);

    List<BoardDTO> getAdminTipBoardList(Criteria cri);

    List<BoardDTO> getAdminRecipeBoardList(Criteria cri);

    Long getBoardTotal(Criteria cri);

    Long getExerBoardTotal(Criteria cri);

    Long getNewsBoardTotal(Criteria cri);

    Long getTipBoardTotal(Criteria cri);

    Long getRecipeBoardTotal(Criteria cri);

//    UserSearch
    TrainerDTO getTrainer(String keyword);

    UserDTO getUser(String keyword);

    List<UserDTO> getUserList(Criteria cri);

    List<TrainerDTO> getTrainerList(Criteria cri);

    Long getUserTotal(Criteria cri);

    Long getTrainerTotal(Criteria cri);

    List<MessageDTO> getMessageList(Criteria cri);

    Long getMessageTotal(Criteria cri);

    List<MessageDTO> getMessageByUser(Criteria cri);

    List<MessageDTO> getMessageByTrainer(Criteria cri);

    Long getMessageTotalByUser(Criteria cri);

    Long getMessageTotalByTrainer(Criteria cri);

    List<ReportDTO> getDoneReportList(Criteria cri);

    Long getDoneReportTotal(Criteria cri);

    ReportDTO getReportDTO(Long reportNum);

    void updateReportYn(Long reportNum);

    void updateReportedUser(String reportedUser);

    void updateReportedTrainer(String reportedUser);

    void insertMessageReportedUser(String reportedUser);

    boolean selectBoard(Long boardNum);

    boolean selectReview(Long boardNum);

    boolean selectMessage(Long boardNum);

    boolean selectChallCert(Long boardNum);

    void deleteReportedBoard(Long boardNum, String boardCategory);

    void deleteReportedReview(Long boardNum);

    void deleteReportedMessage(Long boardNum, String boardCategory);

    void deleteReportedChallCert(Long boardNum);

    void insertMessageDoneReport(String userId);

    void insertMessageCancelReport(String userId);

    TrainerSignUpDTO getSignUpDTO(Long signupNum);

    List<ProfileDTO> getSignUpFile(String userId);

}