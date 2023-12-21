package com.messages.test.web.controller;

import com.messages.test.web.model.MemberForm;
import com.messages.test.web.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping()
    public String join(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "join";
    }

    @PostMapping()
    public String add(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult){

        if(memberService.idDuplicateCheck(memberForm)) {
            bindingResult.reject("idDuplicate", "기본메시지");
        }

        if(bindingResult.hasErrors()) {
            log.info("error ={}",bindingResult);
            return "join";
        }

        memberService.save(memberForm);

        return "redirect:/";
    }

}
