package ru.pcs.web.gamegalaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.web.gamegalaxy.entities.Game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface GamesRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByDeveloperAndGameIdIsNot(String developer, Long id);

    List<Game> findAllByMainGenreAndGameIdIsNot(String genre, Long id);

    List<Game> findAllByDeveloperIgnoreCase(String developer);

    List<Game> findAllByPublisherIgnoreCase(String publisher);

    List<Game> findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(BigDecimal lowestPrice, BigDecimal highestPrice);

    List<Game> findAllByMainGenreOrSideGenre1OrSideGenre2(String mainGenre, String sideGenre1, String sideGenre2);

    List<Game> findFirst9ByOverallScoreIsGreaterThanOrderByOverallScoreDesc(Double score);

    List<Game> findAllByReleaseDateIsGreaterThanEqualOrderByReleaseDateDesc(LocalDate date);

    List<Game> findAllByIsIndieIs(Boolean isIndie);

    List<Game> findAllByNameContainsIgnoreCase(String name);
}
