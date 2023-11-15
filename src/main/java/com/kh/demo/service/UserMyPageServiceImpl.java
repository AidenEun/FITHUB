package com.kh.demo.service;

import com.kh.demo.domain.dto.*;
import com.kh.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Qualifier("UserMyPageServiceImpl")
public class UserMyPageServiceImpl implements UserMyPageService{

    @Autowired
    private UserMyPageMapper umpmapper;

    @Autowired
    private ReplyMapper rmapper;

    @Autowired
    private UserMapper umapper;

    @Autowired
    private ProfileMapper pfmapper;
    @Value("${profile.dir}")
    private String saveFolder;




    @Override
    public boolean registDiary(DiaryDTO diary, MultipartFile[] files) throws Exception {
        return false;
    }

    @Override
    public int registDiary(DiaryDTO diary) {

        return umpmapper.insertDiary(diary);
    }

    @Override
    public boolean modifyDiary(DiaryDTO diary, MultipartFile[] files) {
        return false;
    }

    @Override
    public boolean removeDiary(Long diaryNum) {
        return umpmapper.deleteDiary(diaryNum) == 1;
    }

    @Override
    public DiaryDTO getDiaryDetail(String choicedate, String loginUser) {
        DiaryDTO result = umpmapper.getDiaryDetail(choicedate,loginUser);
        return result;
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
    public Long getMessageTotal(Criteria cri, String userId, String message) {
        if(message.equals("messageAll")){
            return umpmapper.getMessageTotal(cri, userId);
        } else if (message.equals("messageSend")) {
            return umpmapper.getMessageTotalSend(cri,userId);
        } else {
            return umpmapper.getMessageTotalReceive(cri,userId);
        }


    }

    @Override
    public List<MessageDTO> getMessageMyList(Criteria cri, String userId, String message) {
        if(message.equals("messageAll")){
            return umpmapper.getMyMessageAll(cri,userId);
        } else if (message.equals("messageSend")) {
            return umpmapper.getMyMessageSend(cri,userId);
        } else {
            return umpmapper.getMyMessageReceive(cri,userId);
        }
    }

    //나의매칭리스트
    @Override
    public List<UTMatchingDTO> getMyMatchinglist(Criteria cri, String userId) {
            return umpmapper.getMyMatchinglist(cri,userId);
    }

    @Override
    public Long getMatchingTotal(Criteria cri, String userId) {
            return umpmapper.getMatchingTotal(cri, userId);
    }

    @Override
    public ArrayList<String> getMatchingNewlyList(List<UTMatchingDTO> list) throws Exception {
        ArrayList<String> newly_Message = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for(UTMatchingDTO matching : list) {
            Date regdate = df.parse(matching.getMatchingDate());
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
    public boolean updateMatching(UTMatchingDTO utMatching) {
        int row = umpmapper.updateMatching(utMatching);
        if (row != 1) {
            return false;
        }
        return true;
    }



    //보드
    @Override
    public Long getBoardTotal(Criteria cri, String userId) {
        return umpmapper.getBoardTotal(cri,userId);
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
    public Long getBookmarkProductTotal(Criteria cri, String userId) {
        return umpmapper.getBookmarkProductTotal(cri, userId);
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
        return row == 1;
    }

    @Override
    public ProfileDTO getProFileList(String Id) {
        return pfmapper.getProfiles(Id,"P");
    }

    @Override
    public ResponseEntity<Resource> getThumbnailResource(String sysName) throws Exception {
        //경로에 관련된 객체(자원으로 가지고 와야 하는 파일에 대한 경로)
        Path path = Paths.get(saveFolder + sysName);
        //경로에 있는 파일의 MIME타입을 조사해서 그대로 담기
        String contentType = Files.probeContentType(path);
        //응답 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        //해당 경로(path)에 있는 파일에서부터 뻗어나오는 InputStream(Files.newInputStream)을 통해 자원화(InputStreamResource)
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @Override
    public boolean user_profile_modify(UserDTO user, MultipartFile profile, String updateCnt) throws IOException {
        int row = umapper.profileUpdateUser(user);
        if (row != 1) {
            return false;
        }

        List<ProfileDTO> org_profile_list = pfmapper.getFiles(user.getUserId(),"P");
        if(org_profile_list.size()==0 && profile == null ) {
            return true;
        }
        else {
            if(profile != null) {
                boolean flag = false;
                //후에 비즈니스 로직 실패 시 원래대로 복구하기 위해 업로드 성공했던 파일들도 삭제해주어야 한다.
                //업로드 성공한 파일들의 이름을 해당 리스트에 추가하면서 로직을 진행한다.
                String orgname = profile.getOriginalFilename();
                //수정의 경우 중간에 있는 파일은 수정이 되지 않은 경우도 있다.
                //그런 경우의 file의 orgname은 null 이거나 "" 이다.
                //따라서 업로드가 될 필요가 없으므로 continue로 다음 파일로 넘어간다.
                int lastIdx = orgname.lastIndexOf(".");
                String extension = orgname.substring(lastIdx);
                LocalDateTime now = LocalDateTime.now();
                String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
                String systemname = time+ UUID.randomUUID().toString()+extension;

                String path = saveFolder+systemname;
                System.out.println("systemname:"+systemname);
                System.out.println("orgname:"+orgname);

                ProfileDTO profdto = new ProfileDTO();
                profdto.setUserId(user.getUserId());
                profdto.setSysName(systemname);
                profdto.setOrgName(orgname);
                profdto.setProfileCategory("P");

                //실제 파일 업로드
                profile.transferTo(new File(path));

                flag = pfmapper.insertFile(profdto) == 1;

                //강제탈출(실패)
                if(!flag) {
                    //아까 추가했던 systemname들(업로드 성공한 파일의 systemname)을 꺼내오면서
                    //실제 파일이 존재한다면 삭제 진행
                    File file = new File(saveFolder,systemname);
                    if(file.exists()) {
                        file.delete();
                    }
                    pfmapper.deleteBySystemname(systemname);
                }
            }
            //지워져야 할 파일(기존에 있었던 파일들 중 수정된 파일)들의 이름 추출
            String[] deleteNames = updateCnt.split("\\\\");
            for(int i=1;i<deleteNames.length;i++) {
                File file = new File(saveFolder,deleteNames[i]);
                //해당 파일 삭제
                if(file.exists()) {
                    file.delete();
                    //DB상에서도 삭제
                    pfmapper.deleteBySystemname(deleteNames[i]);
                }
            }
            return true;
        }
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
    public List<ChallNoticeBoardDTO> getMyChallenge(Criteria cri, String userId, String challCategory, String challTerm) {
        if(challCategory.equals("challAll")){
            if (challTerm.equals("challengeAll")){
                return umpmapper.getMyChallengeAllAll(cri, userId);

            }else {
                return umpmapper.getMyChallengeAllTerm(cri, userId, challTerm);

            }
        } else{
            if (challTerm.equals("challengeAll")){
                return umpmapper.getMyChallengeCategoryAll(cri, userId, challCategory);

            } else {
                return umpmapper.getMyChallengeCategoryTerm(cri, userId, challCategory,challTerm);

            }
        }
    }


    @Override
    public Long getChallengeTotal(Criteria cri, String userId, String challCategory, String challTerm) {
        if(challCategory.equals("challAll")){
            if (challTerm.equals("challengeAll")){
                return umpmapper.getChallengeAllAllTotal(cri,userId);

            }else {
                return umpmapper.getChallengeAllTermTotal(cri,userId,challTerm);

            }
        } else{
            if (challTerm.equals("challengeAll")){
                return umpmapper.getChallengeCategoryAllTotal(cri,userId,challCategory);

            } else {
                return umpmapper.getChallengeCategoryTermTotal(cri,userId,challCategory,challTerm);

            }
        }
    }

    //트레이너 전환 신청

    @Override
    public boolean insertApplytrainer(TrainerSignUpDTO user, MultipartFile[] files) throws Exception {
        int row = umpmapper.insertApplytrainer(user);
        if(row != 1) {
            return false;
        }
        if(files == null || files.length == 0) {
            return true;
        }
        else {
            //방금 등록한 게시글 번호
            boolean flag = false;
            for(int i=0;i<files.length-1;i++) {
                MultipartFile file = files[i];
                //apple.png
                String orgname = file.getOriginalFilename();
                //5
                int lastIdx = orgname.lastIndexOf(".");
                //.png
                String extension = orgname.substring(lastIdx);

                LocalDateTime now = LocalDateTime.now();
                String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

                //20231005103911237랜덤문자열.png
                String systemname = time+ UUID.randomUUID().toString()+extension;
                System.out.println(systemname);

                //실제 저장될 파일의 경로
                String path = saveFolder+systemname;

                ProfileDTO profdto = new ProfileDTO();
                profdto.setUserId(user.getUserId());
                profdto.setSysName(systemname);
                profdto.setOrgName(orgname);

                //실제 파일 업로드
                file.transferTo(new File(path));

                flag = pfmapper.insertFile(profdto) == 1;

                if(!flag) {
                    //업로드 했던 파일 삭제, 게시글 데이터 삭제
                    return flag;
                }
            }
        }
        return true;
    }

    @Override
    public DiaryDTO getDiaryByNum(Long diaryNum) {
        return umpmapper.getDiaryByNum(diaryNum);
    }

    @Override
    public boolean removeStamp(String userId, String regdate) {
        int removeStamp = umpmapper.removeStamp(userId, regdate);
        System.out.println("삭제된 스템프"+removeStamp);
        boolean result = removeStamp > 0;

        return result;
    }


    @Override
    public boolean addStemp(int sccChallNum, HashMap<String, String> diaryInfo) {
        String userid = diaryInfo.get("userid");
        String diarydate = diaryInfo.get("diarydate");

        return umpmapper.insertStamp(sccChallNum,userid,diarydate) > 0;

    }

    @Override
    public boolean modifyDiary(DiaryDTO diary) {
        return umpmapper.updateDiary(diary)==1;
    }


}
