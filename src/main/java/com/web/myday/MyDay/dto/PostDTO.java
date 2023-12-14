package com.web.myday.MyDay.dto;

import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
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

    public static PostDTO toPostDTO(PostEntity postEntity) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postEntity.getId());
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setContent(postEntity.getContent());
        postDTO.setCreatedAt(LocalDateTime.now());
        return postDTO;
    }
}
