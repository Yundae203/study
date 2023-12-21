package com.messages.test.domain.repository;

import com.messages.test.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberIdAndMemberPw(String memberId, String memberPw);

    Member findByMemberId(String memberId);

}
