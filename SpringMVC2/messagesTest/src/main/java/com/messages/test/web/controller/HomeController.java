package com.messages.test.web.controller;

import com.messages.test.domain.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {

    private final String id = "test";
    private final String pw = "test!";

    @GetMapping("/")
    public String home(@SessionAttribute(value = "loginMember", required = false) String member, Model model) {
        model.addAttribute("test", new Test());
        return "home";
    }

    @PostMapping("/test")
    public String test(@Validated @ModelAttribute Test test, BindingResult bindingResult, HttpServletRequest request) {

        if (test.getId().equals(id) || test.getPw().equals(pw)) {
            bindingResult.reject("global.error",null, "메시지 찾기 실패");
            log.info("error={}", bindingResult.getGlobalError().getDefaultMessage());
        }

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "home";
        }

        // 원래는 멤버 찾는 로직 들어가야 함

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", "test");

        return "redirect:/";
    }
}
