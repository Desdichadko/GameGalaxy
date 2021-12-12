package ru.pcs.web.gamegalaxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pcs.web.gamegalaxy.dto.GameDto;
import ru.pcs.web.gamegalaxy.entities.Game;
import ru.pcs.web.gamegalaxy.repositories.GamesRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    /**
     * Converts GameDto into Game
     *
     * @param gameDto
     * @return Game
     */
    private Game buildGameFromDto(GameDto gameDto) {
        return Game.builder()
                .id(gameDto.getId())
                .name(gameDto.getName())
                .price(gameDto.getPrice())
                .poster(gameDto.getPosterFileName())
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

    /**
     * Updates Game information in repository accordingly with input GameDto
     *
     * @param gameDto game to be updated
     * @param isPosterUpdated should be true if it is needed to update poster as well
     */
    public void updateGameInfo(GameDto gameDto, boolean isPosterUpdated) {
        Game game = gamesRepository.getById(gameDto.getId());
        game.setName(gameDto.getName());
        game.setPrice(gameDto.getPrice());
        game.setDescription(gameDto.getDescription());
        game.setPlatforms(gameDto.getPlatforms());
        game.setYoutubeLink(gameDto.getYoutubeLink());
        game.setMcScore(gameDto.getMcScore());
//        game.setUserScore(gameDto.getUserScore());
        game.setOverallScore(gameDto.getOverallScore());
        game.setDeveloper(gameDto.getDeveloper());
        game.setPublisher(gameDto.getPublisher());
        game.setReleaseDate(gameDto.getReleaseDate());
        game.setSetting(gameDto.getSetting());
        game.setMainGenre(gameDto.getMainGenre());
        game.setSideGenre1(gameDto.getSideGenre1());
        game.setSideGenre2(gameDto.getSideGenre2());
        game.setIsIndie(gameDto.getIsIndie());
        game.setProcessor(gameDto.getProcessor());
        game.setGraphicsCard(gameDto.getGraphicsCard());
        game.setRam(gameDto.getRam());
        game.setFreeMemory(gameDto.getFreeMemory());
        if (isPosterUpdated) {
            game.setPoster(gameDto.getPosterFileName());
        }
        gamesRepository.save(game);
    }

    public List<GameDto> getAllGames(){
        return asDto(gamesRepository.findAll());
    }

    public void deleteGame(Long game_id) {
        gamesRepository.deleteById(game_id);
    }

    /**
     * Takes Game from database and converts it to GameDto
     *
     * @param game_id game id to find
     * @return GameDto
     */
    public GameDto getGameByIdAsDTO(Long game_id){
        return toDto(gamesRepository.getById(game_id));
    }

    /**
     * Converts List of Games to List of GameDto's
     *
     * @param gameList List of Games
     * @return List of GameDto
     */
    private List<GameDto> asDto(List<Game> gameList){
        return gameList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Game getGameById(Long game_id){
        return gamesRepository.getById(game_id);
    }

    /**
     * Converts Game to GameDto
     * Note: unpacks platforms
     *
     * @param game Game to be converted
     * @return GameDto
     */
    private GameDto toDto(Game game){
        return GameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .price(game.getPrice().toString())
                .posterFileName(game.getPoster())
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


    // Filering methods:

    /**
     * Return List of GameDto where with specified main genre and excluded specified game with id
     *
     * @param genre the genre for search
     * @param id of GameDto which will be excluded
     * @return List of GameDto
     */
    public List<GameDto> getGamesByGenreExceptId(String genre, Long id) {
        return asDto(gamesRepository.findAllByMainGenreAndIdIsNot(genre,id));
    }

    public List<GameDto> getGamesByDeveloper(String developer, Long id) {
        return asDto(gamesRepository.findAllByDeveloperAndIdIsNot(developer, id));
    }

    public List<GameDto> getAllGamesWithGenre(String genre) {
        return asDto(gamesRepository.findAllByMainGenreOrSideGenre1OrSideGenre2(genre,genre,genre));
    }

    public List<GameDto> getAllGamesByDeveloper(String developer){
        return asDto(gamesRepository.findAllByDeveloperIgnoreCase(developer));
    }

    public List<GameDto> getAllGamesByPublisher(String publisher){
        return asDto(gamesRepository.findAllByPublisherIgnoreCase(publisher));
    }

    public List<GameDto> getGamesInPriceRange(BigDecimal lPrice, BigDecimal hPrice) {
        return asDto(gamesRepository.findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(lPrice,hPrice));
    }

    public List<GameDto> getTopGamesByOverallScore() {
        return asDto(gamesRepository.findFirst9ByOverallScoreIsGreaterThanOrderByOverallScoreDesc(70.));
    }

    public List<GameDto> getGamesWithDateNewerThan(LocalDate date) {
        return asDto(gamesRepository.findAllByReleaseDateIsGreaterThanEqualOrderByReleaseDateDesc(date));
    }

    public List<GameDto> getIndieGames(){
        return asDto(gamesRepository.findAllByIsIndieIs(true));
    }

    public List<GameDto> getGamesByName(String searchQuery) {
        return asDto(gamesRepository.findAllByNameContainsIgnoreCase(searchQuery));
    }
}
