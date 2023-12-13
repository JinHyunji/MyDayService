package com.web.myday.MyDay.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

}
