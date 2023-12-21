package com.messages.test.web.controller;

import com.messages.test.domain.model.Member;
import com.messages.test.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(@SessionAttribute(name="memberId" ,required = false) String memberId,
                       Model model) {

        if(memberId == null) {
            return "home";
        }

        Member loginMember = memberRepository.findByMemberId(memberId);
        if (loginMember==null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "login-home";
    }
}
