package ru.pcs.web.gamegalaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.web.gamegalaxy.entities.Game;



public interface GamesRepository extends JpaRepository<Game, Long> {
}
