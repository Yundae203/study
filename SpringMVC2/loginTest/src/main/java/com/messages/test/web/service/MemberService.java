package com.messages.test.web.service;

import com.messages.test.domain.model.Member;
import com.messages.test.domain.repository.MemberRepository;
import com.messages.test.web.model.LoginForm;
import com.messages.test.web.model.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(MemberForm memberForm) {

        Member member = Member.builder()
                        .memberId(memberForm.getMemberId())
                        .memberPw(memberForm.getMemberPw())
                        .memberName(memberForm.getMemberName())
                        .memberAge(memberForm.getMemberAge())
                        .build();

        memberRepository.save(member);

        return member;
    }

    public Member login(LoginForm loginForm) {
        return memberRepository.findByMemberIdAndMemberPw(
                                    loginForm.getMemberId(),
                                    loginForm.getMemberPw());
    }

    public boolean idDuplicateCheck(MemberForm memberForm) {
        Member member = memberRepository
                .findByMemberId(memberForm.getMemberId());
        return member != null;
    }
}
