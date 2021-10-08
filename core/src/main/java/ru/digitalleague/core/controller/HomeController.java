package ru.digitalleague.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.digitalleague.core.model.UserAccountEntity;
import ru.digitalleague.core.service.UserAccountService;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final UserAccountService userAccountService;

    @GetMapping("home")
    public UserAccountEntity home(@RequestParam String login) {
        return userAccountService.test(login);
    }

    @GetMapping("auth")
    public String auth(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "auth " + principal.getName();
    }

    @GetMapping("forall")
    public String forall(){
        return "Hello this is page for every one!";
    }

    @GetMapping("admin")
    public String admin(){
        return "Hello this page only for Admin!";
    }
}
