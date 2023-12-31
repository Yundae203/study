package com.messages.test.web.controller;

import com.messages.test.domain.model.Member;
import com.messages.test.web.model.LoginForm;
import com.messages.test.web.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm,
                        BindingResult bindingResult,
                        @RequestParam(name = "redirectURL",defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.info("error ={}", bindingResult);
            return "login";
        }

        Member member = memberService.login(loginForm);

        if (member == null) {
            bindingResult.reject("loginFail", "기본메시지");
            log.info("error ={}", bindingResult);
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("memberId", member.getMemberId());

        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
