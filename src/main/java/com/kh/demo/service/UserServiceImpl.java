package com.kh.demo.service;


import com.kh.demo.domain.dto.AdminDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.UserDTO;
import com.kh.demo.mapper.UserMapper;
import jakarta.jws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        user.setUserId(generateUserId());
        user.setUserJoindate(LocalDateTime.now());
        return umapper.insertUser(user) == 1;
    }

    private String generateUserId() {
        return "USER_" + System.currentTimeMillis();
    }

    @Override
    public boolean checkId(String userId) {
        UserDTO user = umapper.findById(userId);
        return user == null;
    }


    @Override
    public Object login(String userId, String userPw) {
        AdminDTO admin = umapper.findAdminById(userId);
        UserDTO user = umapper.findById(userId);

        if(admin != null) {
            if(admin.getAdminPw().equals(userPw)) {
                return admin;
            }
        }
        else if(user != null){
            if(user.getUserPw().equals(userPw)){
                return user;
            }
        }
        return null;
    }

    @Override
    public UserDTO getDetail(String userId) {
        return umapper.findById(userId);
    }

    @Override
    public List<UserDTO> getSignUpListInUser(Criteria cri) {
        List<UserDTO> userDTOList = umapper.getSignUpListInUser(cri);
//        for(UserDTO userDTO : userDTOList) {
//           .......
//        }
        userDTOList.forEach(userDTO -> {
            String birthDateStr = userDTO.getUserBirth();
            if (birthDateStr != null && birthDateStr.length() == 8) {
                // 현재 날짜 가져오기
                LocalDate currentDate = LocalDate.now();
                int birthYear = Integer.parseInt(birthDateStr.substring(0, 4));
                int birthMonth = Integer.parseInt(birthDateStr.substring(4, 6));
                int birthDay = Integer.parseInt(birthDateStr.substring(6, 8));
                LocalDate birthLocalDate = LocalDate.of(birthYear, birthMonth, birthDay);

                // 나이 계산
                Period period = Period.between(birthLocalDate, currentDate);
                userDTO.setUserAge(period.getYears());
            }
            else {
                userDTO.setUserAge(-1); // 또는 다른 값을 사용하여 오류를 표시
            }
        });
        return userDTOList;
    }



}
