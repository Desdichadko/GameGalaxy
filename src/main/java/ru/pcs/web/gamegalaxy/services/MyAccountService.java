package ru.pcs.web.gamegalaxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pcs.web.gamegalaxy.dto.UserDto;
import ru.pcs.web.gamegalaxy.entities.User;
import ru.pcs.web.gamegalaxy.repositories.UserRepository;


@Transactional
@Service
public class MyAccountService {

    private final UserRepository userRepository;

    @Autowired
    public MyAccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        return userRepository.findUserByEmail(getCurrentUsername());
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public void updateUserInfo(UserDto userDto) {
        User user = getCurrentUser();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userRepository.save(user);
    }
}
