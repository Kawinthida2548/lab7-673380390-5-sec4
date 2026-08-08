package com.example.lab7_6733803909_5_sec4.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.lab7_6733803909_5_sec4.model.Game;
import com.example.lab7_6733803909_5_sec4.repository.GameRepository;
import com.example.lab7_6733803909_5_sec4.strategy.DiscountContext;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DiscountContext discountContext;

    public GameService(GameRepository gameRepository, DiscountContext discountContext) {
        this.gameRepository = gameRepository;
        this.discountContext = discountContext;
    }

    public List<Game> getAllGames() {
        List<Game> games = gameRepository.findAll();
        games.forEach(this::applyDiscountPricing);
        return games;
    }

    public Game getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ไม่พบเกม id: " + id));
        applyDiscountPricing(game);
        return game;
    }

    public double calculateFinalPrice(Game game) {
        if (game == null || game.getPrice() == null) {
            return 0.0;
        }
        return discountContext.calculateFinalPrice(game.getDiscountType(), game.getPrice());
    }

    private void applyDiscountPricing(Game game) {
        if (game == null) {
            return;
        }
        double finalPrice = calculateFinalPrice(game);
        game.setFinalPrice(finalPrice);
    }

    public Game saveGame(Game game) {
        if (game != null) {
            game.setFinalPrice(calculateFinalPrice(game));
        }
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        Game existing = gameRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ไม่พบเกม id: " + id));

        existing.setTitle(updatedGame.getTitle());
        existing.setGenre(updatedGame.getGenre());
        existing.setPlatform(updatedGame.getPlatform());
        existing.setRating(updatedGame.getRating());
        existing.setReleaseDate(updatedGame.getReleaseDate());
        existing.setPrice(updatedGame.getPrice());
        existing.setDiscountType(updatedGame.getDiscountType());

        return gameRepository.save(existing);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

}