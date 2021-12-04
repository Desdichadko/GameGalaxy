package ru.pcs.web.gamegalaxy.excluded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pcs.web.gamegalaxy.models.Game;
import ru.pcs.web.gamegalaxy.repositories.GamesRepository;

import javax.sql.DataSource;
import java.util.List;

@Component
public class GamesRepositoryImpl implements GamesRepository {

    // language = SQL
    private static final String SQL_INSERT_NEW_GAME = "INSERT INTO games (name, price, poster, description, " +
            "platform, youtube_link, mc_score, user_score, overall_score, developer, publisher, release_date, " +
            "setting, main_genre, side_genre1, side_genre2, is_indie, processor, graphics_card, ram, free_memory)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GamesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Game game) {
        jdbcTemplate.update(SQL_INSERT_NEW_GAME,
                 game.getName(), game.getPrice(),game.getPoster(), game.getDescription(),
                game.getPlatforms(),game.getYoutubeLink(), game.getMcScore(), game.getUserScore(),
                game.getOverallScore(), game.getDeveloper(),game.getPublisher(), game.getReleaseDate(),
                game.getSetting(), game.getMainGenre(),game.getSideGenre1(),game.getSideGenre2(),game.getIsIndie(),
                game.getProcessor(), game.getGraphicsCard(), game.getRam(), game.getFreeMemory());
    }



}
