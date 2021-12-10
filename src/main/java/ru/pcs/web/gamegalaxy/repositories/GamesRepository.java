package ru.pcs.web.gamegalaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.web.gamegalaxy.dto.GameDto;
import ru.pcs.web.gamegalaxy.entities.Game;

import java.math.BigDecimal;
import java.util.List;


public interface GamesRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByDeveloperAndIdIsNot(String developer, Long id);

    List<Game> findAllByMainGenreAndIdIsNot(String genre, Long id);

    List<Game> findAllByDeveloperIgnoreCase(String developer);

    List<Game> findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(BigDecimal lowestPrice, BigDecimal highestPrice);

    List<Game> findAllByMainGenreOrSideGenre1OrSideGenre2(String mainGenre, String sideGenre1, String sideGenre2);

}
