package ru.pcs.web.gamegalaxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pcs.web.gamegalaxy.dto.GameDto;
import ru.pcs.web.gamegalaxy.entities.Game;
import ru.pcs.web.gamegalaxy.repositories.GamesRepository;

import java.io.File;
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

    public void addNewGame(GameDto gameDto) {
        Game game = buildGameFromDto(gameDto);
        gamesRepository.save(game);
    }

    private Game buildGameFromDto(GameDto gameDto) {
        return Game.builder()
                .id(gameDto.getId())
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
    }

    public void updateGameInfo(Long id, GameDto gameDto) {
        Game game = buildGameFromDto(gameDto);
        game.setId(id);
        gamesRepository.save(game);
    }

    public List<Game> getAllGames(){
        return gamesRepository.findAll();
    }

    public void deleteGame(Long game_id) {
        gamesRepository.deleteById(game_id);
    }

    public GameDto getGameByIdAsDTO(Long game_id){
        return toDto(gamesRepository.getById(game_id));
    }

    private List<GameDto> asDto(List<Game> gameList){
        return gameList.stream().map(this::toDto).collect(Collectors.toList());
    }


    private GameDto toDto(Game game){
        return GameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .price(game.getPrice().toString())
                .poster(new File(game.getPoster()))
                .description(game.getDescription())
                .platformPS4(game.getPlatforms() != null ?
                        game.getPlatforms().contains("PS4") ? "PS4" : null
                        : null)
                .platformPS5(game.getPlatforms() != null ?
                        game.getPlatforms().contains("PS5") ? "PS5" : null
                        : null)
                .platformPC(game.getPlatforms() != null ?
                        game.getPlatforms().contains("PC") ? "PC" : null
                        : null)
                .youtubeLink(game.getYoutubeLink())
                .mcScore(game.getMcScore() != null ? game.getMcScore().toString() : null)
                .userScore(game.getUserScore() != null ? game.getUserScore().toString() : null)
                .overallScore(game.getOverallScore() != null ? game.getOverallScore().toString() : null)
                .developer(game.getDeveloper())
                .publisher(game.getPublisher())
                .releaseDate(game.getReleaseDate())
                .setting(game.getSetting())
                .mainGenre(game.getMainGenre())
                .sideGenre1(game.getSideGenre1())
                .sideGenre2(game.getSideGenre2())
                .isIndie(game.getIsIndie())
                .processor(game.getProcessor())
                .graphicsCard(game.getGraphicsCard())
                .ram(game.getRam() != null ? game.getRam().toString() : null)
                .freeMemory(game.getFreeMemory() != null ? game.getFreeMemory().toString() : null)
                .build();
    }
}
