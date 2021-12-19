package ru.pcs.web.gamegalaxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pcs.web.gamegalaxy.dto.UserDto;
import ru.pcs.web.gamegalaxy.entities.User;
import ru.pcs.web.gamegalaxy.repositories.UserRepository;


@Transactional
@Service
public class MyAccountService {

    private final UserRepository userRepository;
    private final AuthorizationServiceImpl authorizationService;

    @Autowired
    public MyAccountService(UserRepository userRepository, AuthorizationServiceImpl authorizationService) {
        this.userRepository = userRepository;
        this.authorizationService = authorizationService;
    }

    public void updateUserInfo(UserDto userDto) {
        User user = authorizationService.getCurrentUser();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userRepository.save(user);
    }
}
