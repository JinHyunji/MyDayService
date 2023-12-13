package com.web.myday.MyDay.repository;

import com.web.myday.MyDay.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByMemberId(String memberId);

    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
