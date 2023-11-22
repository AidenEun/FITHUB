package com.kh.demo.controller;

import com.kh.demo.domain.dto.BookMarkDTO;
import com.kh.demo.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bookmark/*")
public class BookmarkController {


    @Autowired @Qualifier("BookMarkServiceImpl")
    private BookMarkService service;

    @PostMapping("bookmark")
    @ResponseBody
    public int bookmark(@ModelAttribute BookMarkDTO bookmark){
        int result = service.insertBookmark(bookmark);
        System.out.println(result);
        return result;
    }

}
