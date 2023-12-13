package com.web.myday.MyDay.service;

import com.web.myday.MyDay.dto.MemberDTO;
import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (byMemberId.isPresent()) {
            MemberEntity memberEntity = byMemberId.get();
            if (memberEntity.getMemberPw().equals(memberDTO.getMemberPw())) {
                return MemberDTO.toMemberDTO(memberEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String idCheck(String memberId) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
        if (byMemberId.isPresent()) {
            return null;
        } else {
            return "ok";
        }
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            return null;
        } else {
            return "ok";
        }
    }
}
