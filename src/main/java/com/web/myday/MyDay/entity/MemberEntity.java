package com.web.myday.MyDay.entity;

import com.web.myday.MyDay.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    private String memberPw;

    @Column(nullable = false)
    private String memberName;

    @Column(name = "postList")
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PostEntity> postEntityList = new ArrayList<>();

    public void addPost(PostEntity postEntity) {
        postEntityList.add(postEntity);
        postEntity.setMember(this);
    }

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPw(memberDTO.getMemberPw());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setPostEntityList(memberDTO.getPostEntityList());
        return memberEntity;
    }
}
