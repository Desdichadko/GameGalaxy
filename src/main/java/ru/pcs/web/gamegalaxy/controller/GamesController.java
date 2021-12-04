package ru.pcs.web.gamegalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.web.gamegalaxy.dto.GameDto;
import ru.pcs.web.gamegalaxy.services.GameService;

@Controller
public class GamesController {

    private final GameService gameService;

    @Autowired
    public GamesController(GameService gameService) {
        this.gameService = gameService;
    }

//    @GetMapping("/games")
//    public String getGamesPage(){
//        gamesRepository.
//        return "games";
//    }

    @PostMapping("/games")
    public String addGame(GameDto gameDto) {
        gameService.add(gameDto);
        return "redirect:/game_add.html";
    }

}
