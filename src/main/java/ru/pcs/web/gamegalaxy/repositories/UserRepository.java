package ru.pcs.web.gamegalaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.web.gamegalaxy.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByEmail(String email);
    User findUserByEmail(String email);
}
