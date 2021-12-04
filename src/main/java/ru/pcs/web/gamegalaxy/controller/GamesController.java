package ru.pcs.web.gamegalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.web.gamegalaxy.dto.GameDto;
import ru.pcs.web.gamegalaxy.models.Game;
import ru.pcs.web.gamegalaxy.repositories.GamesRepository;

@Controller
public class GamesController {
    @Autowired
    private GamesRepository gamesRepository;

    @PostMapping("/games")
    public String addGame(GameDto gameDto) {
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
        gamesRepository.add(game);
        return "redirect:/game_add.html";
    }
}
