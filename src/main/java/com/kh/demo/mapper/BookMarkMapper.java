package com.kh.demo.mapper;


import com.kh.demo.domain.dto.BookMarkDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMarkMapper {

	BookMarkDTO bookCheck(Long boardNum, String userId);

	int insertBookmark(BookMarkDTO bookmark);

	void deleteBookMark(BookMarkDTO bookmark);

}



