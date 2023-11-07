package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.AdminMyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("AdminMyPageServiceImpl")
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

    @Override
    public List<ReportDTO> getDoneReportList(Criteria cri) {
        return adminMyPageMapper.getDoneReportList(cri);
    }

    @Override
    public Long getDoneReportTotal(Criteria cri) {
        return adminMyPageMapper.getDoneReportTotal(cri);
    }

    @Override
    public Object getUserById(String userId) {
        if (adminMyPageMapper.getUserByIdBoolean(userId)){
            return adminMyPageMapper.getUserById(userId);
        }
        else if (adminMyPageMapper.getTrainerByIdBoolean(userId)) {
            return adminMyPageMapper.getTrainerById(userId);
        }
        return null;
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

//    message
    @Override
    public List<MessageDTO> getMessageList(Criteria cri) {
        return adminMyPageMapper.getMessageList(cri);
    }

    @Override
    public Long getMessageTotal(Criteria cri) {
        return adminMyPageMapper.getMessageTotal(cri);
    }

    @Override
    public List<MessageDTO> getMessageByUser(Criteria cri) {
        return adminMyPageMapper.getMessageByUser(cri);
    }

    @Override
    public List<MessageDTO> getMessageByTrainer(Criteria cri) {
        return adminMyPageMapper.getMessageByTrainer(cri);
    }

    @Override
    public Long getMessageTotalByUser(Criteria cri) {
        return adminMyPageMapper.getMessageTotalByUser(cri);
    }

    @Override
    public Long getMessageTotalByTrainer(Criteria cri) {
        return adminMyPageMapper.getMessageTotalByTrainer(cri);
    }

//    Modal
    @Override
    public ReportDTO getReportDTO(Long reportNum) {
        ReportDTO reportDTO =  adminMyPageMapper.getReportDTO(reportNum);
        switch (reportDTO.getReportContent()) {
            case "o1" -> {
                String newReportContent = "욕설, 비방, 차별, 혐오";

                reportDTO.setReportContent(newReportContent);
            }
            case "o2" -> {
                String newReportContent = "홍보, 영리 목적";

                reportDTO.setReportContent(newReportContent);
            }
            case "o3" -> {
                String newReportContent = "불법 정보";

                reportDTO.setReportContent(newReportContent);
            }
            case "o4" -> {
                String newReportContent = "음란, 청소년 유해";

                reportDTO.setReportContent(newReportContent);
            }
            case "o5" -> {
                String newReportContent = "개인 정보 노출, 유포, 거래";

                reportDTO.setReportContent(newReportContent);
            }
            case "o6" -> {
                String newReportContent = "도배, 스팸";

                reportDTO.setReportContent(newReportContent);
            }
        }
        switch (reportDTO.getBoardCategory()) {
            case "tip" -> {
                String newBoardCategory = "꿀팁";

                reportDTO.setBoardCategory(newBoardCategory);
            }
            case "comFree" -> {
                String newBoardCategory = "커뮤니티-자유";

                reportDTO.setBoardCategory(newBoardCategory);
            }
            case "comExer" -> {
                String newBoardCategory = "커뮤니티-운동";

                reportDTO.setBoardCategory(newBoardCategory);
            }
            case "comFood" -> {
                String newBoardCategory = "커뮤니티-식단";

                reportDTO.setBoardCategory(newBoardCategory);
            }
            case "comPhoto" -> {
                String newBoardCategory = "커뮤니티-갤러리";

                reportDTO.setBoardCategory(newBoardCategory);
            }
            case "comQna" -> {
                String newBoardCategory = "커뮤니티-고민QnA";

                reportDTO.setBoardCategory(newBoardCategory);
            }
            case "review" -> {
                String newBoardCategory = "리뷰";

                reportDTO.setBoardCategory(newBoardCategory);
            }
            case "meGeneral" -> {
                String newBoardCategory = "일반 쪽지";

                reportDTO.setBoardCategory(newBoardCategory);
            }
            case "challCert" -> {
                String newBoardCategory = "챌린지 인증";

                reportDTO.setBoardCategory(newBoardCategory);
            }
        }
        return reportDTO;
    }

}
