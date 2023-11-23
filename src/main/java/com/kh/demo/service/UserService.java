package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerDTO;
import com.kh.demo.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    boolean join(UserDTO user);

    boolean checkId(String userid);

    boolean checkNickname(String userNickname);

    Object login(String userid, String userpw);

    UserDTO getDetail(String userid);

    List<UserDTO> getSignUpListInUser(Criteria cri);

    void updateUserAttendance(String userid);

    int getUserAttendance(String userid);

    void updateUserPoint(String userid);

    Long getUserPoint(String userid);

    void resetUserAttendance(String userid);

    List<UserDTO> getUserBoardTotalTop5List();

    List<UserDTO> getUserReplyTotalTop5List();


}