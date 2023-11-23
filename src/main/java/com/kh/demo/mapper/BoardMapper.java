package com.kh.demo.mapper;

import com.kh.demo.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
	//insert
	int insertBoard(BoardDTO board);

	//update
	int updateBoard(BoardDTO board);
	int updateReadCount(Long boardNum);

	int updateBoardLikeCnt(Long boardNum, String boardCategory);
	int updateBoardBookCnt(Long boardNum, String boardCategory);


	//delete
	int deleteBoard(Long boardNum);


	//select
	List<BoardDTO> getList(Criteria cri);
	Long getTotal(Criteria cri);
	Long getTotalWithCategory(Criteria cri);

	List<BoardDTO> getInfoNewsList(Criteria cri);
	List<BoardDTO> getInfoExerList(Criteria cri);
	List<BoardDTO> getInfoFoodList(Criteria cri);
	List<BoardDTO> getInfoTipList(Criteria cri);

	List<BoardDTO> getCommuFreeList(Criteria cri);
	List<BoardDTO> getCommuExerList(Criteria cri);
	List<BoardDTO> getCommuFoodList(Criteria cri);
	List<BoardDTO> getCommuGalleryList(Criteria cri);
	List<BoardDTO> getCommuQnaList(Criteria cri);


	Long getLastNum(String userId);
	BoardDTO findByNum(Long boardNum);

	List<BoardDTO> getAllList(String keyword);

	Long getAllSearchCnt(String keyword);


	List<BoardDTO> getNewsSearchList(String keyword);

	List<BoardDTO> getExerSearchList(String keyword);

	List<BoardDTO> getFoodSearchList(String keyword);

	List<BoardDTO> getTipSearchList(String keyword);

	List<BoardDTO> getCommuSearchList(String keyword);

	BoardDTO getNewsTop1();

	BoardDTO getExerTop1();

	BoardDTO getFoodTop1();

	List<BoardDTO> getBoardTop5List();

	List<BoardDTO> getinfoSearchList(String keyword);

	List<BoardDTO> get12infoSearchList(Criteria cri);

	Long getinfoSearchCnt(Criteria cri);

	List<BoardDTO> get12TipSearchList(Criteria cri);

	Long getTipSearchTotalCnt(Criteria cri);

	List<BoardDTO> get12CommuSearchList(Criteria cri);

	Long getCommuTotalCnt(Criteria cri);


    List<BoardDTO> getUserBoardLikeTop5List();

	List<BoardDTO> getFoodExer(int idx);

	List<BoardDTO> getCommuList(int idx);

	List<BoardDTO> getCategoryList(String category, int idx);
}



