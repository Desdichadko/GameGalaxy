package ru.pcs.web.gamegalaxy.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.web.gamegalaxy.dto.GameDto;
import ru.pcs.web.gamegalaxy.services.GameService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class GamesController {

    private final GameService gameService;

    @Autowired
    public GamesController(GameService gameService) {
        this.gameService = gameService;
    }

    // Admin pages
    @GetMapping("/admin/games")
    public String getGamesPage(Model model) {
        List<GameDto> gameList = gameService.getAllGames();
        model.addAttribute("games", gameList);
        return "admin-list-of-games";
    }

    @PostMapping("/admin/add-new-game")
    public String addGame(GameDto gameDto) {
        gameService.addNewGame(gameDto);
        return "redirect:/admin/add-new-game";
    }

    @GetMapping("/admin/add-new-game")
    public String getAddNewGamePage(){
        return "admin-add-new-game";
    }

    @PostMapping("/admin/games/{game-id}/delete")
    public String deleteGame(@PathVariable("game-id") Long game_id) {
        gameService.deleteGame(game_id);
        return "redirect:/admin/games";
    }

    @GetMapping("/admin/games/{game-id}/details")
    public String getChangeGameInfoPage(Model model, @PathVariable("game-id") Long game_id) {
        GameDto gameDto = gameService.getGameByIdAsDTO(game_id);
        model.addAttribute("gameDto", gameDto);
        return "admin-change-game";
    }

    @PostMapping("/admin/games/{game-id}/details/update")
    public String updateGameInfo(@PathVariable("game-id") Long game_id, GameDto gameDto){
        gameService.updateGameInfo(gameDto);
        return "redirect:/admin/games/{game-id}/details/";
    }

    // User pages
    @GetMapping("/games/gamepage/{game-id}")
    public String getGamePage(@PathVariable("game-id") Long game_id, Model model){
        GameDto game = gameService.getGameByIdAsDTO(game_id);
        List<GameDto> relatedGames = gameService.getGamesByGenreExceptId(game.getMainGenre(), game_id);
        List<GameDto> sameDeveloperGames = gameService.getGamesByDeveloper(game.getDeveloper(), game_id);
        model.addAttribute("game", game);
        model.addAttribute("relatedGames", relatedGames);
        model.addAttribute("sameDeveloperGames", sameDeveloperGames);
        return "single-product";
    }

    @GetMapping("/games")
    public String getAllGamesPage(Model model){
        List<GameDto> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        return "shop";
    }

    @GetMapping("/games/by-developer")
    public String getGamesByDeveloper(String developer, Model model) {
        List<GameDto> gameListDto = gameService.getAllGamesByDeveloper(developer);
        model.addAttribute("allGames", gameListDto);
        return "shop";
    }

    @GetMapping("/games/by-publisher")
    public String getGamesByPublisher(String publisher, Model model) {
        List<GameDto> gameListDto = gameService.getAllGamesByPublisher(publisher);
        model.addAttribute("allGames", gameListDto);
        return "shop";
    }

    @GetMapping("/games/sort-by-price")
    public String sortByPrice(String lowestPrice, String highestPrice, Model model){
        BigDecimal lPrice;
        BigDecimal hPrice;
        if (Strings.isBlank(lowestPrice)) {
            lPrice = BigDecimal.ZERO;
        }
        else {
            lPrice = BigDecimal.valueOf(Double.parseDouble(lowestPrice));
        }
        if (Strings.isBlank(highestPrice)) {
            hPrice = BigDecimal.valueOf(100_000.);
        }
        else {
            hPrice = BigDecimal.valueOf(Double.parseDouble(highestPrice));
        }
        List<GameDto> gameDtoList = gameService.getGamesInPriceRange(lPrice,hPrice);
        model.addAttribute("allGames", gameDtoList);
        return "shop";
    }

    @GetMapping("/games/sort-by-genre")
    public String sortByGenre(String genre, Model model) {
        List<GameDto> gameDtoList = gameService.getAllGamesWithGenre(genre);
        model.addAttribute("allGames", gameDtoList);
        return "shop";
    }

    @GetMapping("/games/sort-by-score")
    public String sortByGenre(Model model) {
        List<GameDto> gameDtoList = gameService.getTopGamesByOverallScore();
        model.addAttribute("allGames", gameDtoList);
        return "shop";
    }

    @GetMapping("/games/newer")
    public String getNewGamesPage(Model model, LocalDate date) {
        List<GameDto> gameDtoList = gameService.getGamesWithDateNewerThan(date);
        model.addAttribute("allGames", gameDtoList);
        return "shop";
    }

    @GetMapping("/games/indie")
    public String getIndieGamesPage(Model model) {
        List<GameDto> gameDtoList = gameService.getIndieGames();
        model.addAttribute("allGames", gameDtoList);
        return "shop";
    }

    @GetMapping("/games/search")
    public String findGamesByName(Model model, String searchQuery) {
        List<GameDto> gameDtoList = gameService.getGamesByName(searchQuery);
        if (gameDtoList.size() == 1) {
            model.addAttribute("game", gameDtoList.get(0));
            return "redirect:/games/gamepage/" + gameDtoList.get(0).getId();
        }
        model.addAttribute("allGames", gameDtoList);
        return "shop";
    }

}
