package com.kh.demo.service;

import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UserServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper umapper;

    @Override
    public boolean join(UserDTO user) {
        return false;
    }

    @Override
    public boolean checkId(String userid) {
        return false;
    }


    @Override
    public UserDTO login(String userid, String userpw) {
        UserDTO user = umapper.findById(userid);
        if(user != null) {
            if(user.getUserPw().equals(userpw)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public UserDTO getDetail(String userid) {
        return umapper.findById(userid);
    }
}
