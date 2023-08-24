package com.example.jpa.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserLogCount {
    private long id;
    private String email;
    private String userNAme;

    private long noticeCount;
    private long noticeLikeCount;
}