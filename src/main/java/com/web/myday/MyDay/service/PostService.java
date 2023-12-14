package com.web.myday.MyDay.service;

import com.web.myday.MyDay.dto.PostDTO;
import com.web.myday.MyDay.entity.PostEntity;
import com.web.myday.MyDay.repository.MemberRepository;
import com.web.myday.MyDay.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostEntity postSave(PostDTO postDTO) {
        PostEntity postEntity = PostEntity.toPostEntity(postDTO);
        postRepository.save(postEntity);
        return postEntity;
    }

    public PostEntity postSave(PostEntity postEntity) {
        postRepository.save(postEntity);
        return postEntity;
    }

    public PostEntity postView(Long id) { return postRepository.findById(id).get(); }

    public void postDelete(Long id) {
        postRepository.deleteById(id);
    }

    public Page<PostEntity> postList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<PostEntity> postSearchList(String searchKeyword, Pageable pageable) {
        return postRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
