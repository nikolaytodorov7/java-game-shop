package ogs.dao.impl;

import ogs.dao.GameRepository;
import ogs.dao.IdGenerator;
import ogs.exception.NonExistingEntityException;
import ogs.model.Game;

class GameRepositoryMemoryImpl extends AbstractPersistableRepository<Long, Game>
        implements GameRepository {

    public GameRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Game findByTitle(String title) throws NonExistingEntityException {
        for (Game game : getModels().values()) {
            var titleOfGame = game.getTitle();
            if(title.equals(titleOfGame)){
                return game;
            }
        }
        throw new NonExistingEntityException("Game with title '" + title + "' does not exist.");
    }

    @Override
    public Game deleteByTitle(String title) throws NonExistingEntityException {
        var game = findByTitle(title);
        deleteById(game.getId());
        return game;
    }
}
