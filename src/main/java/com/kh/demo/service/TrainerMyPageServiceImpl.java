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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Qualifier("TrainerMyPageServiceImpl")
public class TrainerMyPageServiceImpl implements TrainerMyPageService {

    @Autowired
    private TrainerMyPageMapper tmpmapper;

    @Autowired
    private ReplyMapper rmapper;

    @Autowired
    private UserMapper umapper;

    @Autowired
    private TrainerMapper tmapper;

    @Autowired
    private ProfileMapper pfmapper;
    @Value("${profile.dir}")
    private String saveProfileFolder;

    @Autowired
    private FileMapper fmapper;
    @Value("${file.dir}")
    private String saveFolder;

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
    public DiaryDTO checkList(String choicedate, String loginUser) {
//        System.out.println("Service choicedate : "+choicedate);
        return tmpmapper.checkList(choicedate, loginUser);

    }




    /*재우*/

    //메세지
    @Override
    public Long getMessageTotal(Criteria cri, String trainer, String message) {
        if (message.equals("messageAll")) {
            return tmpmapper.getMessageTotal(cri, trainer);
        } else if (message.equals("messageSend")) {
            return tmpmapper.getMessageTotalSend(cri, trainer);
        } else {
            return tmpmapper.getMessageTotalReceive(cri, trainer);
        }
    }

    @Override
    public List<MessageDTO> getMessageMyList(Criteria cri, String trainerId, String message) {
        if (message.equals("messageAll")) {
            return tmpmapper.getMyMessageAll(cri, trainerId);
        } else if (message.equals("messageSend")) {
            return tmpmapper.getMyMessageSend(cri, trainerId);
        } else {
            return tmpmapper.getMyMessageReceive(cri, trainerId);
        }
    }


    @Override
    public Long getMessageLastNum(String userid) {
        return tmpmapper.getMessageLastNum(userid);
    }

    @Override
    public ArrayList<String> getMessageNewlyList(List<MessageDTO> list) throws Exception {
        ArrayList<String> newly_Message = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for (MessageDTO Message : list) {
            Date regdate = df.parse(Message.getSendDate());
            if (now.getTime() - regdate.getTime() < 1000 * 60 * 60 * 2) {
                newly_Message.add("O");
            } else {
                newly_Message.add("X");
            }
        }
        return newly_Message;
    }

    @Override
    public boolean updateMatching(UTMatchingDTO utMatching, String trainerCheck) {
        int row = tmpmapper.updateMatching(utMatching,trainerCheck);
        if (row != 1) {
            return false;
        }
        return true;
    }

    //보드

    @Override
    public Long getBoardTotal(Criteria cri, String trainerId) {
        return tmpmapper.getBoardTotal(cri, trainerId);
    }

    @Override
    public BoardDTO getBoardDetail(Long boardnum) {
        return tmpmapper.findBoardByNum(boardnum);
    }

    @Override
    public Long getBoardLastNum(String userid) {
        return tmpmapper.getBoardLastNum(userid);
    }

    @Override
    public ArrayList<String> getBoardNewlyList(List<BoardDTO> list) throws Exception {
        ArrayList<String> newly_board = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for (BoardDTO board : list) {
            Date regdate = df.parse(board.getRegdate());
            if (now.getTime() - regdate.getTime() < 1000 * 60 * 60 * 2) {
                newly_board.add("O");
            } else {
                newly_board.add("X");
            }
        }
        return newly_board;
    }

    @Override
    public ArrayList<Integer> getBoardReplyCntList(List<BoardDTO> list) {
        ArrayList<Integer> reply_cnt_list = new ArrayList<>();
        for (BoardDTO board : list) {
            reply_cnt_list.add(rmapper.getTotal(board.getBoardNum()));
        }
        return reply_cnt_list;
    }

    @Override
    public ArrayList<String> getBoardRecentReplyList(List<BoardDTO> list) {
        ArrayList<String> recent_reply = new ArrayList<>();
        for (BoardDTO board : list) {
            if (rmapper.getRecentReply(board.getBoardNum()) >= 5) {
                recent_reply.add("O");
            } else {
                recent_reply.add("X");
            }
        }
        return recent_reply;
    }

    @Override
    public List<BoardDTO> getBoardMyList(Criteria cri, String trainerId) {
        return tmpmapper.getMyBoard(cri, trainerId);
    }


    //북마크
    @Override
    public Long getBookmarkTotal(Criteria cri, String userId) {
        return tmpmapper.getBookmarkTotal(cri, userId);
    }

    @Override
    public Long getBookmarkProductTotal(Criteria cri, String userId) {
        return tmpmapper.getBookmarkProductTotal(cri, userId);
    }

    @Override
    public Long getBookmarkLastNum(String userid) {
        return tmpmapper.getBookmarkLastNum(userid);
    }

    @Override
    public ArrayList<String> getBookmarkNewlyList(List<BookMarkDTO> list) throws Exception {
        ArrayList<String> newly_Message = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        for (BookMarkDTO bookmark : list) {
            Date regdate = df.parse(bookmark.getRegdate());
            if (now.getTime() - regdate.getTime() < 1000 * 60 * 60 * 2) {
                newly_Message.add("O");
            } else {
                newly_Message.add("X");
            }
        }
        return newly_Message;
    }


    @Override
    public List<BoardDTO> getMyBookmark(Criteria cri, String trainerId) {
        return tmpmapper.getMyBookmark(cri, trainerId);
    }

    @Override
    public List<ProductBoardDTO> getMyBookmarkProduct(Criteria cri, String userId) {
        return tmpmapper.getMyBookmarkProduct(cri, userId);
    }


    //내정보수정
    @Override
    public TrainerDTO getUserDetail(String userid) {
        return tmapper.findById(userid);
    }

    @Override
    public boolean user_modify(TrainerDTO user) {
        int row = tmapper.updateUser(user);
        if (row != 1) {
            return false;
        }
        return true;
    }

    //유저구독
    @Override
    public List<UserDTO> getMyScribe(Criteria cri, String userId) {
        return tmpmapper.getMyScribe(cri, userId);
    }

    @Override
    public Long getScribeTotal(Criteria cri, String userId) {
        return tmpmapper.getScribeTotal(cri, userId);
    }

    //내 챌린지
    @Override
    public Long getChallengeTotal(Criteria cri, String userId, String challCategory, String challTerm) {
        if (challCategory.equals("challAll")) {
            if (challTerm.equals("challengeAll")) {
                return tmpmapper.getChallengeAllAllTotal(cri, userId);

            } else {
                return tmpmapper.getChallengeAllTermTotal(cri, userId, challTerm);

            }
        } else {
            if (challTerm.equals("challengeAll")) {
                return tmpmapper.getChallengeCategoryAllTotal(cri, userId, challCategory);

            } else {
                return tmpmapper.getChallengeCategoryTermTotal(cri, userId, challCategory, challTerm);

            }
        }
    }

    @Override
    public List<ChallNoticeBoardDTO> getMyChallenge(Criteria cri, String userId, String challCategory, String challTerm) {
        if (challCategory.equals("challAll")) {
            if (challTerm.equals("challengeAll")) {
                return tmpmapper.getMyChallengeAllAll(cri, userId);

            } else {
                return tmpmapper.getMyChallengeAllTerm(cri, userId, challTerm);

            }
        } else {
            if (challTerm.equals("challengeAll")) {
                return tmpmapper.getMyChallengeCategoryAll(cri, userId, challCategory);

            } else {
                return tmpmapper.getMyChallengeCategoryTerm(cri, userId, challCategory, challTerm);

            }
        }
    }

    //내 프로필
    @Override
    public ProfileDTO getProFileList(String trainerId) {
        return pfmapper.getProfiles(trainerId,"P");
    }
    @Override
    public List<ProfileDTO> getFileList(String trainerId) {
        return pfmapper.getFiles(trainerId, "C");
    }

    @Override
    public ResponseEntity<Resource> getThumbnailResource(String sysName) throws Exception {
        //경로에 관련된 객체(자원으로 가지고 와야 하는 파일에 대한 경로)
        Path path = Paths.get(saveProfileFolder + sysName);
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
    public boolean trainer_modify(TrainerDTO trainer, MultipartFile[] files, MultipartFile profile, String updateCnt) throws IOException {
        int row = tmpmapper.updateTrainer(trainer);
        if (row != 1) {
            return false;
        }



        List<ProfileDTO> org_file_list = pfmapper.getFiles(trainer.getTrainerId(),"C");
        List<ProfileDTO> org_profile_list = pfmapper.getFiles(trainer.getTrainerId(),"P");
        if((org_file_list.size()==0 && (files == null || files.length == 0)) && (org_profile_list.size()==0 && profile == null )) {
            return true;
        }
        else {
            if(files != null) {
                boolean flag = false;
                //후에 비즈니스 로직 실패 시 원래대로 복구하기 위해 업로드 성공했던 파일들도 삭제해주어야 한다.
                //업로드 성공한 파일들의 이름을 해당 리스트에 추가하면서 로직을 진행한다.
                ArrayList<String> sysnames = new ArrayList<>();
                System.out.println("service : "+files.length);
                for(int i=0;i<files.length-1;i++) {
                    MultipartFile file = files[i];
                    String orgname = file.getOriginalFilename();
                    //수정의 경우 중간에 있는 파일은 수정이 되지 않은 경우도 있다.
                    //그런 경우의 file의 orgname은 null 이거나 "" 이다.
                    //따라서 업로드가 될 필요가 없으므로 continue로 다음 파일로 넘어간다.
                    if(orgname == null || orgname.equals("")) {
                        continue;
                    }
                    int lastIdx = orgname.lastIndexOf(".");
                    String extension = orgname.substring(lastIdx);
                    LocalDateTime now = LocalDateTime.now();
                    String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
                    String systemname = time+ UUID.randomUUID().toString()+extension;
                    sysnames.add(systemname);

                    String path = saveProfileFolder+systemname;

                    ProfileDTO profdto = new ProfileDTO();
                    profdto.setUserId(trainer.getTrainerId());
                    profdto.setSysName(systemname);
                    profdto.setOrgName(orgname);
                    profdto.setProfileCategory("C");

                    //실제 파일 업로드
                    file.transferTo(new File(path));

                    flag = pfmapper.insertFile(profdto) == 1;
                    if(!flag) {
                        break;
                    }
                }
                //강제탈출(실패)
                if(!flag) {
                    //아까 추가했던 systemname들(업로드 성공한 파일의 systemname)을 꺼내오면서
                    //실제 파일이 존재한다면 삭제 진행
                    for(String systemname : sysnames) {
                        File file = new File(saveProfileFolder,systemname);
                        if(file.exists()) {
                            file.delete();
                        }
                        pfmapper.deleteBySystemname(systemname);
                    }
                }
            }
            if(profile.getOriginalFilename() != null && !profile.getOriginalFilename().equals("")) {
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

                    String path = saveProfileFolder+systemname;
                    System.out.println("systemname:"+systemname);
                    System.out.println("orgname:"+orgname);

                    ProfileDTO profdto = new ProfileDTO();
                    profdto.setUserId(trainer.getTrainerId());
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
                    File file = new File(saveProfileFolder,systemname);
                    if(file.exists()) {
                        file.delete();
                    }
                    pfmapper.deleteBySystemname(systemname);
                }
            }
            //지워져야 할 파일(기존에 있었던 파일들 중 수정된 파일)들의 이름 추출
            String[] deleteNames = updateCnt.split("\\\\");
            for(int i=1;i<deleteNames.length;i++) {
                File file = new File(saveProfileFolder,deleteNames[i]);
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



    @Override
    public List<UTMatchingDTO> getMyMatchinglist(Criteria cri, String trainerId) {
        return tmpmapper.getMyMatchinglist(cri,trainerId);
    }

    @Override
    public Long getMatchingTotal(Criteria cri, String trainerId) {
        return tmpmapper.getMatchingTotal(cri, trainerId);
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


}

