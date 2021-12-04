package ru.pcs.web.gamegalaxy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String poster;
    private String description;
    private String platforms;
    private String youtubeLink;
    private Double mcScore;
    private Double userScore;
    private Double overallScore;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String setting;
    private String mainGenre;
    private String sideGenre1;
    private String sideGenre2;
    private Boolean isIndie;
    private String processor;
    private String graphicsCard;
    private Integer ram;
    private Double freeMemory;
}


