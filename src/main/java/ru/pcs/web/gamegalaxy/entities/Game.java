package ru.pcs.web.gamegalaxy.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class Game {
    @Id
    @Column(name = "game_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private String poster;

    private String description;

    private String platforms;

    @Column(name = "youtube_link")
    private String youtubeLink;

    @Column(name = "mc_score")
    private Double mcScore;

    @Column(name = "user_score")
    private Double userScore;

    @Column(name = "overall_score")
    private Double overallScore;

    private String developer;

    private String publisher;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private String setting;

    @Column(name = "main_genre")
    private String mainGenre;

    @Column(name = "side_genre1")
    private String sideGenre1;

    @Column(name = "side_genre2")
    private String sideGenre2;

    @Column(name = "is_indie")
    private Boolean isIndie;

    private String processor;

    @Column(name = "graphics_card")
    private String graphicsCard;

    private Integer ram;

    @Column(name = "free_memory")
    private Double freeMemory;


}
