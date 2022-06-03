package ogs.service.impl;

import ogs.dao.GameRepository;
import ogs.exception.ConstraintViolationException;
import ogs.exception.InvalidEntityDataException;
import ogs.exception.NonExistingEntityException;
import ogs.model.Game;
import ogs.service.GameService;
import ogs.util.GameValidator;

import java.sql.Timestamp;
import java.util.Collection;

public class GameServiceImpl implements GameService {
    private final GameRepository gameRepo;
    private final GameValidator gameValidator;

    public GameServiceImpl(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
        this.gameValidator = new GameValidator();
    }

    public GameServiceImpl(GameRepository gameRepo, GameValidator gameValidator) {
        this.gameRepo = gameRepo;
        this.gameValidator = gameValidator;
    }

    @Override
    public void loadData() {
        gameRepo.load();
    }

    @Override
    public void saveData() {
        gameRepo.save();
    }

    @Override
    public Collection<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @Override
    public Game getGameById(Long id) throws NonExistingEntityException {
        var game = gameRepo.findById(id);
        if (game == null)
            throw new NonExistingEntityException("Game with ID='" + id + "' does not exist.");
        return gameRepo.findById(id);
    }

    @Override
    public Game addGame(Game game) throws InvalidEntityDataException {
        try {
            gameValidator.validate(game);
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityDataException(
                    String.format("Error creating game '%s'", game.getTitle()),
                    ex
            );
        }
        game.setCreated(new Timestamp(System.currentTimeMillis()));
        game.setModified(new Timestamp(System.currentTimeMillis()));
        var created = gameRepo.create(game);
        gameRepo.save();
        return created;
    }

    @Override
    public Game updateGame(Game game) throws NonExistingEntityException, InvalidEntityDataException {
        try {
            gameValidator.validate(game);
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityDataException(
                    String.format("Error updating game '%s'", game.getTitle()),
                    ex
            );
        }
        game.setModified(new Timestamp(System.currentTimeMillis()));
        var updated = gameRepo.update(game);
        gameRepo.save();
        return updated;
    }

    @Override
    public Game deleteGameById(Long id) throws NonExistingEntityException {
        var deleted = gameRepo.deleteById(id);
        gameRepo.save();
        return deleted;
    }

    @Override
    public long count() {
        return gameRepo.count();
    }

    @Override
    public Game getGameByTitle(String title) throws NonExistingEntityException {
        var game = gameRepo.findByTitle(title);
        if (game == null)
            throw new NonExistingEntityException("Game with Title='" + title + "' does not exist.");
        return game;
    }

    @Override
    public Game deleteGameByTitle(String title) throws NonExistingEntityException {
        var deleted = gameRepo.deleteByTitle(title);
        gameRepo.save();
        return deleted;
    }

    @Override
    public Collection<Game> getAllGamesByUser() {
        return gameRepo.findAll();
    }

    @Override
    public void gamePromotion(Game game, double promo) {
        game.setPrice((game.getPrice() * (1 - promo / 100)));
    }
}
