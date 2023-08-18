package com.example.jpa.notice.controller;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.NoticeModel;
import com.example.jpa.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiNoticeController {

    private final NoticeRepository noticeRepository;

//    @GetMapping("/api/notice")
//    public String noticeString() {
//        return "공지사항입니다.";
//    }

//    @GetMapping("/api/notice")
//    public NoticeModel notice() {
//        LocalDateTime regDate = LocalDateTime.of(2023, 8, 18, 0, 0);
//
//        NoticeModel notice = new NoticeModel();
//        notice.setId(1);
//        notice.setTitle("공지사항입니다.");
//        notice.setContents("공지사항 내용입니다.");
//        notice.setRegDate(regDate);
//        return notice;
//    }

//    @GetMapping("/api/notice")
//    public List<NoticeModel> notice() {
//        List<NoticeModel> noticeList = new ArrayList<>();
//
//        noticeList.add(NoticeModel.builder()
//                .id(1)
//                .title("첫번째 공지사항입니다.")
//                .contents("첫번째 공지사항 내용입니다.")
//                .regDate(LocalDateTime.now())
//                .build());
//
//        noticeList.add(NoticeModel.builder()
//                .id(2)
//                .title("두번째 공지사항입니다.")
//                .contents("두번째 공지사항 내용입니다.")
//                .regDate(LocalDateTime.now())
//                .build());
//
//        return noticeList;
//    }

    @GetMapping("/api/notice")
    public List<NoticeModel> notice() {
        List<NoticeModel> noticeList = new ArrayList<>();
        return noticeList;
    }

    @GetMapping("/api/notice/count")
    public int noticeCount() {
        return 10;
    }

//    @PostMapping("/api/notice")
//    public NoticeModel addNotice(@RequestParam String title, @RequestParam String contents) {
//        return NoticeModel.builder()
//                .id(1)
//                .title(title)
//                .contents(contents)
//                .regDate(LocalDateTime.now())
//                .build();
//    }

//    @PostMapping("/api/notice")
//    public NoticeModel addNotice(NoticeModel noticeModel) {
//        noticeModel.setId(2);
//        noticeModel.setRegDate(LocalDateTime.now());
//        return noticeModel;
//    }

//    @PostMapping("/api/notice")
//    public NoticeModel addNotice(@RequestBody NoticeModel noticeModel) {
//        noticeModel.setId(3);
//        noticeModel.setRegDate(LocalDateTime.now());
//        return noticeModel;
//    }

//    @PostMapping("/api/notice")
//    public Notice addNotice(@RequestBody NoticeInput noticeInput) {
//        Notice notice = Notice.builder()
//                .title(noticeInput.getTitle())
//                .contents(noticeInput.getContents())
//                .regDate(LocalDateTime.now())
//                .build();
//
//        noticeRepository.save(notice);
//        return notice;
//    }

    @PostMapping("/api/notice")
    public Notice addNotice(@RequestBody NoticeInput noticeInput) {
        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .regDate(LocalDateTime.now())
                .hits(0)
                .likes(0)
                .build();

        Notice resultNotice = noticeRepository.save(notice);
        return resultNotice;
    }
}
