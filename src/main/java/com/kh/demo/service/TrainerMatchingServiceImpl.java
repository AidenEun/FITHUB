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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("TrainerMatchingServiceImpl")
public class TrainerMatchingServiceImpl implements TrainerMatchingService {


    @Autowired
    private TrainerMatchingMapper tmmapper;
    @Autowired
    private TrainerMapper tmapper;
    @Autowired
    private ReviewMapper rvmapper;
    @Autowired
    private ProfileMapper pfmapper;
    @Value("${profile.dir}")
    private String saveFolder;

    @Override
    public List<TrainerMatchingBoardDTO> getmatchingList(Criteria cri) {
        List<TrainerMatchingBoardDTO> list = tmmapper.getList(cri);

        for (TrainerMatchingBoardDTO board : list) {
            // 각각의 board에 대한 trainer 정보를 가져와서 설정
            ProfileDTO profileInfo = pfmapper.getProfileInfo(board.getTrainerId());
            TrainerDTO trainerInfo = tmapper.getTrainerInfo(board.getTrainerId());

            board.setProfileInfo(profileInfo);
            board.setTrainerInfo(trainerInfo);


        }
        return list;
    }

    @Override
    public ResponseEntity<Resource> getThumbnailResource(String sysName) throws Exception{
        //경로에 관련된 객체(자원으로 가지고 와야 하는 파일에 대한 경로)
        Path path = Paths.get(saveFolder+sysName);
        //경로에 있는 파일의 MIME타입을 조사해서 그대로 담기
        String contentType = Files.probeContentType(path);
        //응답 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        //해당 경로(path)에 있는 파일에서부터 뻗어나오는 InputStream(Files.newInputStream)을 통해 자원화(InputStreamResource)
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource,headers, HttpStatus.OK);
    }
    @Override
    public boolean regist(TrainerMatchingBoardDTO board){
        int row = tmmapper.insertBoard(board);
        if(row != 1) {
            return false;
        }
        return true;
    };

    @Override
    public TrainerMatchingBoardDTO boardView(Long boardNum){
        TrainerMatchingBoardDTO list = tmmapper.boardView(boardNum);

        return list;
    };
    @Override
    public ProfileDTO getProfileInfo(String trainerId){
        ProfileDTO profileInfo = pfmapper.getProfileInfo(trainerId);
        return profileInfo;
    };
    @Override
    public ProfileDTO getCareerInfo(String trainerId){
        ProfileDTO careerInfo = pfmapper.getCareerInfo(trainerId);
        return careerInfo;
    };
    @Override
    public TrainerDTO getTrainerInfo(String trainerId){
        TrainerDTO trainerInfo = tmapper.getTrainerInfo(trainerId);
        return trainerInfo;
    };





    @Override
    public void updateViewCount(Long boardNum){
        tmmapper.updateViewCount(boardNum);
    };

/*    @Override
    public ArrayList<Integer> getReviewCntList(List<TrainerMatchingBoardDTO> list) {
        ArrayList<Integer> review_cnt_list = new ArrayList<>();
        for(TrainerMatchingBoardDTO board : list) {
            review_cnt_list.add(rvmapper.getTotal(board.getBoardNum()));
        }
        return review_cnt_list;
    }*/





    @Override
    public Long getLastNum(String trainerId){
        return tmmapper.getLastNum(trainerId);
    };

    public Object getUserByNickname(String trainerNickname){
        if (tmmapper.getUserByIdBoolean(trainerNickname)){
            return tmmapper.getUserById(trainerNickname);
        }
        else if (tmmapper.getTrainerByIdBoolean(trainerNickname)) {
            return tmmapper.getTrainerById(trainerNickname);
        }
        return null;
    };
    @Override
    public TrainerMatchingBoardDTO getBoardBytrainerId(String trainerId){
            return tmmapper.getBoardBytrainerId(trainerId);
    };
    @Override
    public void saveMatching(UTMatchingDTO newMatching){
        tmmapper.saveMatching(newMatching);
    };
    @Override
    public UTMatchingDTO getutBytrainerId(String trainerId) {
        Object result = tmmapper.getutBytrainerId(trainerId);

        if (result != null) {
            // 데이터가 존재할 때 UTMatchingDTO로 변환하여 반환
            return (UTMatchingDTO) result;
        } else {
            // 데이터가 없을 때
            return null;
        }
    }

    public void saveMessage(MessageDTO newMessage){
        tmmapper.saveMessage(newMessage);
    }

//    통합검색
    @Override
    public List<TrainerMatchingBoardDTO> get12matchingSearchList(Criteria cri) {
        return tmmapper.get12matchingSearchList(cri);
    }

    @Override
    public Long getmatchingTotal(Criteria cri) {
        return tmmapper.getmatchingTotal(cri);
    }

    ;
    @Override
    public List<TrainerMatchingBoardDTO> getMachingSearchList(String keyword) {
        return tmmapper.getMachingSearchList(keyword);
    }

    public SubscribeDTO checkSubs(SubscribeDTO newSubscribe){return tmmapper.getcheckSubs(newSubscribe);};

    public SubscribeDTO clickSubs(SubscribeDTO newSubscribe){
        SubscribeDTO result = tmmapper.getcheckSubs(newSubscribe);

        if(result != null){
            return tmmapper.getdeleteSubs(newSubscribe);
        }
        else {
            return tmmapper.getinsertSubs(newSubscribe);
        }

    };

    @Override
    public boolean modify(TrainerMatchingBoardDTO board) {
        int row = tmmapper.updateBoard(board);
        if(row != 1) {
            return false;
        }

        return true;

    }



    @Override
    public boolean remove(String loginUser, Long boardNum) {
        TrainerMatchingBoardDTO board = tmmapper.findByNum(boardNum);
        if(board.getTrainerId().equals(loginUser)) {
            return tmmapper.deleteBoard(boardNum) == 1;
        }
        return false;
    }

    @Override
    public Long getTotal(Criteria cri) {
        return tmmapper.getTotal(cri);
    }

    @Override
    public TrainerMatchingBoardDTO getDetail(Long boardNum) {
        return tmmapper.findByNum(boardNum);
    }
    @Override
    public List<TrainerMatchingBoardDTO> getAllBoards(Long boardNum){
        return tmmapper.getAllBoards(boardNum);
    };
    @Override
    public List<TrainerDTO> getTrainerNickname(String trainerId){return tmapper.getTrainerNickname(trainerId);}
}