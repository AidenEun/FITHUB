package com.kh.demo.service;

import com.kh.demo.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

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
        return null;
    }
}
