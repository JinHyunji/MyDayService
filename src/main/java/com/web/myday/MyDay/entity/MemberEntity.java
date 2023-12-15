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

}
