package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.TrainerMatchingBoardDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("TrainerMatchingServiceImpl")
public class TrainerMatchingServiceImpl implements TrainerMatchingService {

    @Override
    public List<TrainerMatchingBoardDTO> getmatchingList() {
        // 임시로 빈 리스트를 반환
        return new ArrayList<>();
    }

    @Override
    public Long getTotal(Criteria cri) {
        // 임시로 0L을 반환
        return 0L;
    }

    @Override
    public ArrayList<String> getRecentReplyList(List<TrainerMatchingBoardDTO> list) {
        // 임시로 빈 ArrayList를 반환
        return new ArrayList<>();
    }

    @Override
    public ArrayList<String> getNewlyBoardList(List<TrainerMatchingBoardDTO> list) throws Exception {
        // 임시로 빈 ArrayList를 반환
        return new ArrayList<>();
    }

}
