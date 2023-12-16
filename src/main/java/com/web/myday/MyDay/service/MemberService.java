package com.web.myday.MyDay.service;

import com.web.myday.MyDay.dto.MemberDTO;
import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
import com.web.myday.MyDay.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO); // dto를 entity로 바꾸기
        memberRepository.save(memberEntity); // entity를 DB에 저장
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다.
            return null;
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail()); // 이메일로 회원조회
        if (byMemberEmail.isPresent()) { // 회원이 있으면(이메일 존재)
            if (byMemberEmail.get().getMemberPw().equals(memberDTO.getMemberPw())) { // 조회한 회원비번과 입력한 회원비번이 같으면
                return MemberDTO.toMemberDTO(byMemberEmail.get()); // 회원 반환
            } else { // 다르면
                return null;
            }
        } else { // 없으면
            return null;
        }
    }
}
