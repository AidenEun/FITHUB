package com.kh.demo.service;

import com.kh.demo.domain.dto.BoardDTO;
import com.kh.demo.domain.dto.BookMarkDTO;
import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ProductBoardDTO;
import com.kh.demo.mapper.BookMarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("BookMarkServiceImpl")
public class BookMarkServiceImpl implements BookMarkService{

	@Autowired
	private BookMarkMapper bookmarkmapper;



}













