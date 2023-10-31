package com.kh.demo.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String userNickname;
    private String userPw;
    private int weightGoal;
    private int caloriesGoal;
    private String userTel;
    private String userMail;
    private int userHeight;
    private int userWeight;
    private String userGender;
    private String userBirth;
    private String userJoindate;
    private Long userPoint;
    private int userReportedcnt;
    private String userCategory;

    public int getAge() {
        if (userBirth > 0) {
            // 현재 날짜 가져오기
            LocalDate currentDate = LocalDate.now();

            // 생년월일 문자열을 LocalDate로 변환
            String birthDateStr = String.valueOf(userBirth);
            int birthYear = Integer.parseInt(birthDateStr.substring(0, 4));
            int birthMonth = Integer.parseInt(birthDateStr.substring(4, 6));
            LocalDate birthLocalDate = LocalDate.of(birthYear, birthMonth, 1);

            // 나이 계산
            Period period = Period.between(birthLocalDate, currentDate);
            return period.getYears();
        }
        return 0; // 생년월일이 없는 경우 기본값
    }
}
