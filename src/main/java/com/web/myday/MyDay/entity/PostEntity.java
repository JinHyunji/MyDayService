package com.web.myday.MyDay.entity;

import com.web.myday.MyDay.dto.PostDTO;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    public static PostEntity toPostEntity(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(postDTO.getId());
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setContent(postDTO.getContent());
        postEntity.setCreatedAt(LocalDateTime.now());
        return postEntity;
    }
}
