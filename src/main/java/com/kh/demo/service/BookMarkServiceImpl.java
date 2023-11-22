package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.BoardMapper;
import com.kh.demo.mapper.BookMarkMapper;
import com.kh.demo.mapper.ProductBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("BookMarkServiceImpl")
public class BookMarkServiceImpl implements BookMarkService{

	@Autowired
	private BookMarkMapper bookmarkmapper;
	@Autowired
	private BoardMapper bmapper;
	@Autowired
	private ProductBoardMapper pbmapper;


	@Override
	public int insertBookmark(BookMarkDTO bookmark) {
		Long boardNum = bookmark.getBoardNum();
		String loginUser = bookmark.getUserId();
		//북마크가 DB에 저장되어있는 것이 없으면 0 리턴
		int result = 0;

		System.out.println(result);

		//북마크가 이미 있는지 확인하는 코드
		BookMarkDTO check = bookmarkmapper.bookCheck(boardNum, loginUser);

		System.out.println(check);

		//checkLike가 null이면 북마크가 없는 상태이므로 정보 저장
		//checkLike가 null이 아니면 북마크가 있는 상태이므로 정보 삭제
		if(check==null){
			//insert의 리턴값은 DB에 성공적으로 insert된 갯수를 보내므로 result=1
			result = bookmarkmapper.insertBookmark(bookmark);
			updateBookmarkCount(bookmark);
		}else {
			bookmarkmapper.deleteBookMark(bookmark);
			updateBookmarkCount(bookmark);
		}
		//0 or 1이 담겨져서 @Controller에 보냄
		return result;
	}

	@Override
	public void updateBookmarkCount(BookMarkDTO bookmark) {
		Long boardNum = bookmark.getBoardNum();
		String boardCategory = bookmark.getBoardCategory();

		if (boardCategory.equals("prodFood")) {
			pbmapper.updateProductBookCnt(boardNum, boardCategory);
		}  else if ("prodExer".equals(boardCategory)){
			pbmapper.updateProductBookCnt(boardNum, boardCategory);
		}
		else {
			bmapper.updateBoardBookCnt(boardNum, boardCategory);
		}
	}
}













