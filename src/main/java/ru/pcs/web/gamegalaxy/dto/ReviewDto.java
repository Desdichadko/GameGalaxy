package ru.pcs.web.gamegalaxy.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pcs.web.gamegalaxy.entities.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private Long reviewId;

    private Long gameId;

    private User user;

    @NotNull
    private String reviewBody;

    private LocalDateTime publishDate;

}
