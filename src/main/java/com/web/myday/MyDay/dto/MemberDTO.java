package com.web.myday.MyDay.dto;

import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberEmail;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPw(memberEntity.getMemberPw());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        return memberDTO;
    }
}
