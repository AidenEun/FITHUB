package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    boolean join(UserDTO user);

    boolean checkId(String userid);

    Object login(String userid, String userpw);

    UserDTO getDetail(String userid);

    List<UserDTO> getSignUpListInUser(Criteria cri);

}
