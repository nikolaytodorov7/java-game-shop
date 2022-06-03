package ogs.dao;

import ogs.exception.NonExistingEntityException;
import ogs.model.Game;
import ogs.model.User;

public interface GameRepository extends PersistableRepository<Long, Game> {
    Game findByTitle(String title) throws NonExistingEntityException;
    Game deleteByTitle(String title) throws NonExistingEntityException;
}
