package com.web.myday.MyDay.dto;

import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDTO {

    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private MemberEntity member;

}
