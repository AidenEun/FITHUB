package com.kh.demo.controller;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReviewDTO;
import com.kh.demo.domain.dto.ReviewPageDTO;
import com.kh.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/review/*")
@RestController
public class ReviewContoroller {
    @Autowired
    private ReviewService service;

    @GetMapping("/getUtmatchingNum/{boardNum}")
    public ResponseEntity<String> getUtmatchingNum(@PathVariable("boardNum") Long boardNum) {
        String utmatchingNum = service.getUtmatchingNum(boardNum);
        return utmatchingNum != null ? new ResponseEntity<>(utmatchingNum, HttpStatus.OK) :
                new ResponseEntity<>("not exists", HttpStatus.OK);
    }


    //ResponseEntity : 서버의 상태코드, 응답 메세지, 응답 데이터 등을 담을 수 있는 타입
    //consumes : 이 메소드가 호출될 때 소비할 데이터의 타입(넘겨지는 RequestBody의 데이터 타입)
    //@RequestBody : 넘겨지는 body의 데이터 타입을 해석해서 해당 파라미터에 채워넣기
    @PostMapping(value = "regist", consumes = "application/json")
    public ResponseEntity<String> regist(@RequestBody ReviewDTO review){
        boolean check = service.regist(review);
        Long reviewNum = service.getLastNum(review.getUserId());

        return check ? new ResponseEntity<String>(reviewNum+"", HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // /reply/pages/100/1 : 100번 게시글의 1 페이지 댓글 리스트
    @GetMapping(value = "/pages/{boardNum}/{pageNum}")
    public ResponseEntity<ReviewPageDTO> getList(
            @PathVariable("boardNum") Long boardNum,
            @PathVariable("pageNum") int pageNum
    ){
        Criteria cri = new Criteria(pageNum, 5);
        return new ResponseEntity<ReviewPageDTO>(service.getList(cri, boardNum), HttpStatus.OK);
    }

    //@DeleteMapping : REST 방식의 설계 이용 시 삭제 요청을 받을 때 사용하는 매핑 방식
    //produces : 이 메소드가 호출된 결과로 생산해낼 데이터의 타입(돌려주는 ResponseBody의 데이터 타입)
    @DeleteMapping(value = "{reviewNum}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("reviewNum") Long reviewNum){
        return service.remove(reviewNum) ?
                new ResponseEntity<String>("success",HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //PUT
    //	모든 데이터들을 다 전달, 자원의 전체 수정, 자원 내의 모든 필드를 전달해야 함
    //PATCH
    //	자원의 일부 수정, 수정할 필드만 전송
//	@PatchMapping(value = "{replynum}", consumes = "application/json")
    @PutMapping(value = "{reviewNum}", consumes = "application/json")
    public ResponseEntity<String> modify(@RequestBody ReviewDTO review){
        return service.modify(review) ?
                new ResponseEntity<String>("success",HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

