package com.web.myday.MyDay.service;

import com.web.myday.MyDay.dto.PostDTO;
import com.web.myday.MyDay.entity.PostEntity;
import com.web.myday.MyDay.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void postSave(PostDTO postDTO) {
        PostEntity postEntity = PostEntity.toPostEntity(postDTO);
        postRepository.save(postEntity);
    }
}
