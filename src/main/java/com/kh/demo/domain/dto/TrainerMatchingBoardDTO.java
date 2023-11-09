package com.kh.demo.domain.dto;

import lombok.Data;
@Data
public class TrainerMatchingBoardDTO {
    private long boardNum;
    private String trainerId;
    private String boardTitle;
    private String boardContent;
    private String trainerZipcode;
    private String trainerAddr;
    private String trainerAddrdetail;
    private String trainerAddretc;
    private String availConsultWeeks;
    private String availConsultTime;
    private String expirationTime;
    private long likeCnt;
    private long bookmarkCnt;
    private long viewCnt;
    private String regdate;


    private TrainerDTO trainerInfo;

    public TrainerDTO getTrainerInfo() {
        return trainerInfo;
    }

    public void setTrainerInfo(TrainerDTO trainerInfo) {
        this.trainerInfo = trainerInfo;
    }

    public void setProfileInfo(ProfileDTO profileInfo) {
    }
}
