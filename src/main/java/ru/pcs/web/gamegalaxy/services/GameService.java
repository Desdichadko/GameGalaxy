package ru.pcs.web.gamegalaxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pcs.web.gamegalaxy.dto.GameDto;
import ru.pcs.web.gamegalaxy.entities.Game;
import ru.pcs.web.gamegalaxy.repositories.GamesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class GameService {

    private final GamesRepository gamesRepository;

    @Autowired
    public GameService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public void add(GameDto gameDto) {
        Game game = Game.builder()
                .name(gameDto.getName())
                .price(gameDto.getPrice())
                .poster(gameDto.getPoster())
                .description(gameDto.getDescription())
                .platforms(gameDto.getPlatforms())
                .youtubeLink(gameDto.getYoutubeLink())
                .mcScore(gameDto.getMcScore())
                .userScore(gameDto.getUserScore())
                .overallScore(gameDto.getOverallScore())
                .developer(gameDto.getDeveloper())
                .publisher(gameDto.getPublisher())
                .releaseDate(gameDto.getReleaseDate())
                .setting(gameDto.getSetting())
                .mainGenre(gameDto.getMainGenre())
                .sideGenre1(gameDto.getSideGenre1())
                .sideGenre2(gameDto.getSideGenre2())
                .isIndie(gameDto.getIsIndie())
                .processor(gameDto.getProcessor())
                .graphicsCard(gameDto.getGraphicsCard())
                .ram(gameDto.getRam())
                .freeMemory(gameDto.getFreeMemory())
                .build();
        gamesRepository.save(game);
    }

}
