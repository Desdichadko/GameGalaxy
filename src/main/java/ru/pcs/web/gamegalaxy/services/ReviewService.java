package ru.pcs.web.gamegalaxy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pcs.web.gamegalaxy.dto.ReviewDto;
import ru.pcs.web.gamegalaxy.entities.Game;
import ru.pcs.web.gamegalaxy.entities.Review;

import ru.pcs.web.gamegalaxy.entities.User;
import ru.pcs.web.gamegalaxy.repositories.ReviewRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AuthorizationServiceImpl authorizationService;
    private final GameService gameService;

    public void addNewReview(ReviewDto reviewDto) {
        reviewDto.setUser(authorizationService.getCurrentUser());
        reviewDto.setPublishDate(LocalDateTime.now());
        Review review = getEntityFromDto(reviewDto);
        reviewRepository.save(review);
    }

    private Review getEntityFromDto(ReviewDto reviewDto) {

        return Review.builder()
                .game(gameService.getGameById(reviewDto.getGameId()))
                .user(reviewDto.getUser())
                .reviewBody(reviewDto.getReviewBody())
                .publishDate(reviewDto.getPublishDate())
                .build();
    }

    private ReviewDto getDtoFromEntity(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .reviewBody(review.getReviewBody())
                .user(review.getUser())
                .gameId(review.getGame().getGameId())
                .publishDate(review.getPublishDate())
                .build();
    }

    public List<ReviewDto> getAllGameReviews(Long gameId) {
        return asDto(reviewRepository.getAllReviewsByGame_GameIdOrderByPublishDateAsc(gameId));
    }

    public List<ReviewDto> asDto(List<Review> reviewList) {
        return reviewList.stream().map(this::getDtoFromEntity).collect(Collectors.toList());
    }
}
