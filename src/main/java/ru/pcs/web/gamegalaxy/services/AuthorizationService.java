package ru.pcs.web.gamegalaxy.services;

import ru.pcs.web.gamegalaxy.dto.UserDto;

public interface AuthorizationService {
    void signUpUser(UserDto userDto);
}
