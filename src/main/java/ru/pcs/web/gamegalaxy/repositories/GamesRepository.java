package ru.pcs.web.gamegalaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.web.gamegalaxy.entities.Game;

import java.util.List;


public interface GamesRepository extends JpaRepository<Game, Long> {

//   List<Game> findAll();

}
