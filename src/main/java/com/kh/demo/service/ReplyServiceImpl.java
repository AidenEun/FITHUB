package com.kh.demo.service;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReplyDTO;
import com.kh.demo.domain.dto.ReplyPageDTO;
import com.kh.demo.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper rmapper;

    @Override
    public boolean regist(ReplyDTO reply) {
        return rmapper.insertReply(reply) == 1;
    }

    @Override
    public boolean modify(ReplyDTO reply) {
        return rmapper.updateReply(reply) == 1;
    }

    @Override
    public boolean remove(Long replyNum) {
        return rmapper.deleteReply(replyNum) == 1;
    }

    @Override
    public ReplyPageDTO getList(Criteria cri, Long boardNum) {
        return new ReplyPageDTO(rmapper.getTotal(boardNum), rmapper.getList(cri, boardNum));
    }

    @Override
    public Long getLastNum(String userId) {
        return rmapper.getLastNum(userId);
    }
}
