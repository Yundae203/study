package com.messages.test.web.controller;

import com.messages.test.domain.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("test", new Test());
        return "home";
    }

    @PostMapping("/test")
    public String test(@Validated @ModelAttribute Test test, BindingResult bindingResult) {

        log.info("id={} pw={}", test.getId(), test.getPw());
        
        if (test.getId().equals("")) {
            log.info("마사카");
        }
        
        if (test.getId().equals("") && test.getPw().equals("")) {
            bindingResult.reject("global.error",null, "메시지 찾기 실패");
            log.info("error={}", bindingResult.getGlobalError().getDefaultMessage());
        }

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "home";
        }

        return "redirect:/";
    }
}
