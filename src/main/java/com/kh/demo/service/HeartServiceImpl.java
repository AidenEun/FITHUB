package com.kh.demo.service;

import com.kh.demo.domain.dto.LikeDTO;
import com.kh.demo.mapper.BoardMapper;
import com.kh.demo.mapper.HeartMapper;
import com.kh.demo.mapper.ProductBoardMapper;
import com.kh.demo.mapper.TrainerMatchingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("HeartServiceImpl")
public class HeartServiceImpl implements HeartService {


    @Autowired
    private HeartMapper hmapper;
    @Autowired
    private BoardMapper bmapper;
    @Autowired
    private ProductBoardMapper pbmapper;

    @Autowired
    private TrainerMatchingMapper tmmapper;

    @Override
    public int insertHeart(LikeDTO heart) {
        Long boardNum = heart.getBoardNum();
        String loginUser = heart.getUserId();
        //좋아요가 DB에 저장되어있는 것이 없으면 0 리턴
        int result = 0;
        //좋아요가 이미 있는지 확인하는 코드
        LikeDTO check = hmapper.likeCheck(boardNum, loginUser);

        //checkLike가 null이면 좋아요가 없는 상태이므로 정보 저장
        //checkLike가 null이 아니면 좋아요가 있는 상태이므로 정보 삭제
        if(check==null){
            //insert의 리턴값은 DB에 성공적으로 insert된 갯수를 보내므로 result=1
            result = hmapper.insertHeart(heart);
            updateLikeCount(heart);
        }else {
            hmapper.deleteHeart(heart);
            updateLikeCount(heart);
        }
        //0 or 1이 담겨져서 @Controller에 보냄
        return result;
    }

    public void updateLikeCount(LikeDTO heart) {
        Long boardNum = heart.getBoardNum();
        String boardCategory = heart.getBoardCategory();

        if (boardCategory.equals("prodFood")) {
            pbmapper.updateProductLikeCnt(boardNum, boardCategory);
        }  else if ("prodExer".equals(boardCategory)){
            pbmapper.updateProductLikeCnt(boardNum, boardCategory);
        }  else if (boardCategory.equals("matching")) {
            tmmapper.updateMatchingLikeCnt(boardNum);
        }   else {
            bmapper.updateBoardLikeCnt(boardNum, boardCategory);
        }
    }
}
