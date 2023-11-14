package com.kh.demo.mapper;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.BookMarkDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
	//insert
	int insertBoard(BoardDTO board);

	//update
	int updateBoard(BoardDTO board);
	int updateReadCount(Long boardNum);

	//delete
	int deleteBoard(Long boardNum);

	//select
	List<BoardDTO> getList(Criteria cri);
	Long getTotal(Criteria cri);

	List<BoardDTO> getNewsList(Criteria cri);
	List<BoardDTO> getExerList(Criteria cri);
	List<BoardDTO> getFoodList(Criteria cri);
	List<BoardDTO> getTipList(Criteria cri);

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

//    int getNewsSearchCnt(String keyword);
}



