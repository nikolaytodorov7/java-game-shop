package ogs.service;

import ogs.exception.InvalidEntityDataException;
import ogs.exception.NonExistingEntityException;
import ogs.model.Game;
import ogs.model.User;

import java.util.Collection;

public interface GameService {
    void loadData();

    void saveData();

    Collection<Game> getAllGames();

    Game getGameById(Long id) throws NonExistingEntityException;

    Game addGame(Game game) throws InvalidEntityDataException;

    Game updateGame(Game game) throws NonExistingEntityException, InvalidEntityDataException;

    Game deleteGameById(Long id) throws NonExistingEntityException;

    long count();

    Game getGameByTitle(String title) throws NonExistingEntityException;

    Game deleteGameByTitle(String title) throws NonExistingEntityException;


    Collection<Game> getAllGamesByUser();

    void gamePromotion(Game game, double promo);
}
