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

    @GetMapping("/games")
    public String getGamesPage(Model model) {
        List<Game> gameList = gameService.getAllGames();
        model.addAttribute("games", gameList);
        return "games";
    }

    @PostMapping("/games")
    public String addGame(GameDto gameDto) {
        gameService.addNewGame(gameDto);
        return "redirect:/game_add.html";
    }

    @PostMapping("/games/{game-id}/delete")
    public String deleteGame(@PathVariable("game-id") Long game_id) {
        gameService.deleteGame(game_id);
        return "redirect:/games";
    }

    @GetMapping("/games/{game-id}/details")
    public String getGameInfoPage(Model model, @PathVariable("game-id") Long game_id) {
        GameDto gameDto = gameService.getGameByIdAsDTO(game_id);
        model.addAttribute("game", gameDto);
        return "game-info";
    }

    @PostMapping("/games/{game-id}/details/update")
    public String updateGameInfo(@PathVariable("game-id") Long game_id, GameDto gameDto){
        gameService.updateGameInfo(game_id, gameDto);
        return "redirect:/games/{game-id}/details/";
    }

}
