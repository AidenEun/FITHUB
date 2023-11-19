package com.kh.demo.mapper;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ProductBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductBoardMapper {
	//insert
	int insertBoard(ProductBoardDTO pboard);

	//update
	int updateBoard(ProductBoardDTO pboard);
	int updateReadCount(Long boardNum);

	//delete
	int deleteBoard(Long boardNum);

	//select
	List<ProductBoardDTO> getList(Criteria cri);
	Long getTotal(Criteria cri);

	Long getLastNum(String adminId);
	ProductBoardDTO findByNum(Long boardNum);

	List<ProductBoardDTO> getProdFoodList(Criteria cri);
	List<ProductBoardDTO> getProdExerList(Criteria cri);


}



