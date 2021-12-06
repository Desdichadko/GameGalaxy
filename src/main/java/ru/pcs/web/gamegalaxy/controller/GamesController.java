package ru.pcs.web.gamegalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.web.gamegalaxy.dto.GameDto;
import ru.pcs.web.gamegalaxy.entities.Game;
import ru.pcs.web.gamegalaxy.services.GameService;

import java.util.List;

@Controller
public class GamesController {

    private final GameService gameService;

    @Autowired
    public GamesController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/admin/games")
    public String getGamesPage(Model model) {
        List<Game> gameList = gameService.getAllGames();
        model.addAttribute("games", gameList);
        return "admin-listofgames";
    }

    @PostMapping("/admin/games")
    public String addGame(GameDto gameDto) {
        gameService.addNewGame(gameDto);
        return "redirect:/game_add.html";
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

    @GetMapping("/games/gamepage{game-id}")
    public String getGamePage(@PathVariable("game-id") Long game_id, Model model){
        Game game = gameService.getGameById(game_id);
        model.addAttribute("game", game);
        return "game-page";
    }

}
