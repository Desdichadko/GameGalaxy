package ru.pcs.web.gamegalaxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pcs.web.gamegalaxy.dto.UserDto;
import ru.pcs.web.gamegalaxy.services.MyAccountService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/my-account")
public class MyAccountController {

    private final MyAccountService myAccountService;

    @GetMapping
    public String getMyAccountPage(Model model) {
        model.addAttribute("user", myAccountService.getCurrentUser());
        return "my-account";
    }

    @PostMapping("/change-personal-info")
    public String changePersonalInfo(UserDto userDto) {
        myAccountService.updateUserInfo(userDto);
        return "redirect:/my-account";
    }
}
