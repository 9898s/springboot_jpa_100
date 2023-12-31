package com.example.jpa.notice.model;

import com.example.jpa.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NoticeReponse {
    private Long id;

    private Long regUserId;
    private String regUserName;

    private String title;
    private String contents;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private int hits;
    private int likes;

    public static NoticeReponse of(Notice notice) {
        return NoticeReponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .contents(notice.getContents())
                .regDate(notice.getRegDate())
                .regUserId(notice.getUser().getId())
                .regUserName(notice.getUser().getUserName())
                .updateDate(notice.getUpdateDate())
                .hits(notice.getHits())
                .likes(notice.getLikes())
                .build();
    }
}
