package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("UserMyPageServiceImpl")
public class UserMyPageServiceImpl implements UserMyPageService{

    @Autowired
    private UserMyPageMapper umpmapper;

    @Autowired
    private MessageMapper megmapper;

    @Autowired
    private BoardMapper bmapper;

    @Autowired
    private ReplyMapper rmapper;

    @Autowired
    private BookMarkMapper bookmarkmapper;

    @Autowired
    private UserMapper umapper;

    @Autowired
    private TrainerMapper subScribemapper;







    @Override
    public boolean registDiary(DiaryDTO diary, MultipartFile[] files) throws Exception {
        return false;
    }

    @Override
    public boolean modifyDiary(DiaryDTO diary, MultipartFile[] files) {
        return false;
    }

    @Override
    public boolean removeDiary(Long diaryNum) {
        return false;
    }

    @Override
    public DiaryDTO getDiaryDetail(String choicedate) {
        return null;
    }

    @Override
    public List<DiaryDTO> getDiaryList(String userid) {
        return null;
    }

    @Override
    public DiaryDTO checkList(String choicedate,String loginUser) {
//        System.out.println("Service choicedate : "+choicedate);
        return umpmapper.checkList(choicedate,loginUser);

    }




    /*재우*/

    //메세지
    @Override
    public Long getMessageTotal(Criteria cri) {
        return umpmapper.getMessageTotal(cri);
    }


    @Override
    public Long getMessageLastNum(String userid) {
        return umpmapper.getMessageLastNum(userid);
    }

    @Override
    public ArrayList<String> getMessageNewlyList(List<MessageDTO> list) throws Exception {
        ArrayList<String> newly_Message = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for(MessageDTO Message : list) {
            Date regdate = df.parse(Message.getSendDate());
            if(now.getTime() - regdate.getTime() < 1000*60*60*2) {
                newly_Message.add("O");
            }
            else {
                newly_Message.add("X");
            }
        }
        return newly_Message;
    }

    @Override
    public List<MessageDTO> getMessageMyList(Criteria cri, String userId) {
        return umpmapper.getMyMessage(cri,userId);
    }



    //보드
    @Override
    public Long getBoardTotal(Criteria cri) {
        return umpmapper.getBoardTotal(cri);
    }

    @Override
    public BoardDTO getBoardDetail(Long boardnum) {
        return umpmapper.findBoardByNum(boardnum);
    }

    @Override
    public Long getBoardLastNum(String userid) {
        return umpmapper.getBoardLastNum(userid);
    }

    @Override
    public ArrayList<String> getBoardNewlyList(List<BoardDTO> list) throws Exception {
        ArrayList<String> newly_board = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for(BoardDTO board : list) {
            Date regdate = df.parse(board.getRegdate());
            if(now.getTime() - regdate.getTime() < 1000*60*60*2) {
                newly_board.add("O");
            }
            else {
                newly_board.add("X");
            }
        }
        return newly_board;
    }

    @Override
    public ArrayList<Integer> getBoardReplyCntList(List<BoardDTO> list) {
        ArrayList<Integer> reply_cnt_list = new ArrayList<>();
        for(BoardDTO board : list) {
            reply_cnt_list.add(rmapper.getTotal(board.getBoardNum()));
        }
        return reply_cnt_list;
    }

    @Override
    public ArrayList<String> getBoardRecentReplyList(List<BoardDTO> list) {
        ArrayList<String> recent_reply = new ArrayList<>();
        for(BoardDTO board : list) {
            if(rmapper.getRecentReply(board.getBoardNum()) >= 5) {
                recent_reply.add("O");
            }
            else {
                recent_reply.add("X");
            }
        }
        return recent_reply;
    }

    @Override
    public List<BoardDTO> getBoardMyList(Criteria cri, String userId) {
        return umpmapper.getMyBoard(cri,userId);
    }



    //북마크
    @Override
    public Long getBookmarkTotal(Criteria cri, String userId) {
        return umpmapper.getBookmarkTotal(cri,userId);
    }


    @Override
    public Long getBookmarkLastNum(String userid) {
        return umpmapper.getBookmarkLastNum(userid);
    }

    @Override
    public ArrayList<String> getBookmarkNewlyList(List<BookMarkDTO> list) throws Exception {
        ArrayList<String> newly_Message = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for(BookMarkDTO bookmark : list) {
            Date regdate = df.parse(bookmark.getRegdate());
            if(now.getTime() - regdate.getTime() < 1000*60*60*2) {
                newly_Message.add("O");
            }
            else {
                newly_Message.add("X");
            }
        }
        return newly_Message;
    }


    @Override
    public List<BoardDTO> getMyBookmark(Criteria cri, String userId) {
        return umpmapper.getMyBookmark(cri, userId);
    }

    @Override
    public List<ProductBoardDTO> getMyBookmarkProduct(Criteria cri, String userId) {
        return umpmapper.getMyBookmarkProduct(cri, userId);
    }


    //내정보수정
    @Override
    public UserDTO getUserDetail(String userid) {
        return umapper.findById(userid);
    }

    @Override
    public boolean user_modify(UserDTO user) {
        int row = umapper.updateUser(user);
        if (row != 1) {
            return false;
        }
        return true;
    }

    //내구독
    @Override
    public List<TrainerDTO> getMyScribe(Criteria cri, String userId) {
        return umpmapper.getMyScribe(cri, userId);
    }
    @Override
    public Long getScribeTotal(Criteria cri, String userId) {
        return umpmapper.getScribeTotal(cri,userId);
    }

    //내 챌린지
    @Override
    public Long getChallengeTotal(Criteria cri, String userId) {
        return null;
    }

    @Override
    public List<ChallNoticeBoardDTO> getMyChallenge(Criteria cri, String userId) {
        return null;
    }

}
