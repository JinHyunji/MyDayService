package com.web.myday.MyDay.service;

import com.web.myday.MyDay.dto.MemberDTO;
import com.web.myday.MyDay.dto.PostDTO;
import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
import com.web.myday.MyDay.repository.MemberRepository;
import com.web.myday.MyDay.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 게시글 저장 및 회원의 게시글리스트에 게시글 추가
    public PostEntity savePost(Long memberId, PostDTO postDTO) {
        PostEntity postEntity = PostEntity.toPostEntity(postDTO);
        if (postEntity.getTitle() == "") {
            postEntity.setTitle("(제목없음)");
            Optional<MemberEntity> memberEntity = memberRepository.findById(memberId);
            memberEntity.get().addPost(postEntity);
            return postEntity;
        } else {
            Optional<MemberEntity> byId = memberRepository.findById(memberId);
            byId.get().addPost(postEntity);
            return postEntity;
        }
    }

    public PostEntity postView(Long postId) {
        return postRepository.findById(postId).get();
    }

    public PostEntity updatePost(Long memberId, PostDTO postDTO) {
        PostEntity postEntity = postRepository.findById(postDTO.getPostId()).get();
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setContent(postDTO.getContent());
        Optional<MemberEntity> byId = memberRepository.findById(memberId);
        byId.get().addPost(postEntity);
        return postEntity;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<PostEntity> postListByDes(Long memberId) {
        return postRepository
                .findAll()
                .stream()
                .filter(post -> post.getMember().getId() == memberId)
                .filter(post -> post.getCreatedAt() != null)
                .sorted(Comparator.comparing(PostEntity::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
