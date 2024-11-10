package com.server.record.controller;

import com.server.record.domain.NoticeBoard;
import com.server.record.domain.NoticeBoardDTO;
import com.server.record.domain.UserTable;
import com.server.record.service.NoticeBoardService;
import com.server.record.service.UserTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/noticeBorad/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class NoticeBoardCotroller {

    @Autowired
    private NoticeBoardService service;
    @Autowired
    private UserTableService userTableService;

    // 보여주기
    @GetMapping("viewBoard")
    public ResponseEntity viewBoard() {
        List<NoticeBoard> noticeBoard = service.viewAll();
        List<NoticeBoardDTO> list = new ArrayList<>();
        for(NoticeBoard board : noticeBoard){
            UserTable userTable = userTableService.UserIdCheck(board.getUserCode());
            NoticeBoardDTO dto = NoticeBoardDTO.builder()
                    .noticeBoardCode(board.getNoticeBoardCode())
                    .noticeBoardH1(board.getNoticeBoardH1())
                    .noticeBoardText(board.getNoticeBoardText())
                    .noticeBoardCreated(board.getNoticeBoardCreated())
                    .userTable(userTable)
                    .build();
            list.add(dto);
        }
        return ResponseEntity.ok().body(list);
    }

    // 생성하기
    @PostMapping("createNoticeBoard")
    public ResponseEntity createNoticeBoard(@RequestBody NoticeBoard dto){
//        log.info("1 : " + dto);
//        NoticeBoard noticeBoard = NoticeBoard.builder()
//                .userCode(dto.getUserCode())
//                .noticeBoardH1(dto.getNoticeBoardH1())
//                .noticeBoardText(dto.getNoticeBoardText())
//                .build();
//
//        log.info("2 : " + noticeBoard);
        service.createNoticeBoard(dto);
        return ResponseEntity.ok().build();
    }
    // 디테일 정보가져오기
    @GetMapping("detailViewNoticeBoard/{code}")
    public ResponseEntity detailViewNoticeBoard(@PathVariable int code){
        return ResponseEntity.ok().body(service.detailView(code));
    }

}
