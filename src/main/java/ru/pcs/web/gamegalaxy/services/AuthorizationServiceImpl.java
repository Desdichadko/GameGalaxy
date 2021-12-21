package ru.pcs.web.gamegalaxy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.pcs.web.gamegalaxy.dto.UserDto;
import ru.pcs.web.gamegalaxy.entities.User;
import ru.pcs.web.gamegalaxy.repositories.UserRepository;

@RequiredArgsConstructor
@Component
public class AuthorizationServiceImpl implements AuthorizationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void signUpUser(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(User.Role.USER)
                .hashPassword(passwordEncoder.encode(userDto.getPassword()))
                .build();
        userRepository.save(user);
    }

    public User getCurrentUser() {
        return userRepository.findUserByEmail(getCurrentUsername());
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
