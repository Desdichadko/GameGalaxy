package ru.pcs.web.gamegalaxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.web.gamegalaxy.dto.ReviewDto;
import ru.pcs.web.gamegalaxy.services.ReviewService;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping("/add-review")
    public String addNewComment(ReviewDto reviewDto) {
        reviewService.addNewReview(reviewDto);
        return "redirect:/games/gamepage/" + reviewDto.getGameId();
    }
}
