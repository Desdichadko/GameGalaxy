package ru.pcs.web.gamegalaxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.web.gamegalaxy.dto.UserDto;
import ru.pcs.web.gamegalaxy.services.AuthorizationService;

@RequiredArgsConstructor
@Controller
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/sign-up")
    public String signUp(UserDto userDto) {
        authorizationService.signUpUser(userDto);
        return "redirect:/sign-in";
    }

    @GetMapping("/sign-in")
    public String getAuthorizationPage() {
        return "login";
    }

}
