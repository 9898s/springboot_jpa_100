package com.example.jpa.board.controller;

import com.example.jpa.board.entity.BoardBadReport;
import com.example.jpa.board.model.BoardReplyInput;
import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.common.model.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiAdminBoardController {
    private final BoardService boardService;

    @GetMapping("/api/admin/board/badreport")
    public ResponseEntity<?> badReport() {
        List<BoardBadReport> list = boardService.badReportList();
        return ResponseResult.success(list);
    }

    @PostMapping("/api/admin/board/{id}/reply")
    public ResponseEntity<?> reply(@PathVariable Long id, @RequestBody BoardReplyInput boardReplyInput) {
        ServiceResult result = boardService.replyBoard(id, boardReplyInput);
        return ResponseResult.result(result);
    }
}
