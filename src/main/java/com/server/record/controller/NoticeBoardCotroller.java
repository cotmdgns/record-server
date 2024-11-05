package com.server.record.controller;

import com.server.record.domain.NoticeBoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class NoticeBoardCotroller {
    // 보여주기
    @GetMapping("viewBoard")
    public ResponseEntity viewBorad() {
        return ResponseEntity.ok().build();
    }

    // 생성하기
    @PostMapping("createBoard")
    public ResponseEntity createBorad(NoticeBoardDTO dto){
        log.info("" + dto);
        return ResponseEntity.ok().build();
    }

}
