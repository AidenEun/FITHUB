package com.kh.demo.service;


import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.mapper.UserMapper;
import jakarta.jws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

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
        UserDTO user = umapper.findById(userid);
        return user != null;
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

    @Override
    public List<UserDTO> getSignUpListInUser(Criteria cri) {
        List<UserDTO> userDTOList = umapper.getSignUpListInUser(cri);
//        for(UserDTO userDTO : userDTOList) {
//           .......
//        }
        userDTOList.forEach(userDTO -> {
            String birthDateStr = userDTO.getUserBirth();
            if (birthDateStr != null && birthDateStr.length() >= 6) {
                // 현재 날짜 가져오기
                LocalDate currentDate = LocalDate.now();
                int birthYear = Integer.parseInt(birthDateStr.substring(0, 4));
                int birthMonth = Integer.parseInt(birthDateStr.substring(4, 6));
                LocalDate birthLocalDate = LocalDate.of(birthYear, birthMonth, 1);

                // 나이 계산
                Period period = Period.between(birthLocalDate, currentDate);
                userDTO.setUserAge(period.getYears());
            }
        });
        return userDTOList;
    }



}
