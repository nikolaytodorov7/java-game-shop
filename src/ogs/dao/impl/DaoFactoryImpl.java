package ogs.dao.impl;

import ogs.dao.CategoryRepository;
import ogs.dao.DaoFactory;
import ogs.dao.GameRepository;
import ogs.dao.UserRepository;

public class DaoFactoryImpl implements DaoFactory {

    @Override
    public UserRepositoryMemoryImpl createUserRepository() {
        return new UserRepositoryMemoryImpl(new IdGeneratorImpl());
    }

    @Override
    public UserRepository createUserRepositoryFile(String dbFileNAme) {
        return new UserRepositoryFileImpl(new IdGeneratorImpl(), dbFileNAme);
    }

    @Override
    public CategoryRepositoryMemoryImpl createCategoryRepository() {
        return new CategoryRepositoryMemoryImpl(new IdGeneratorImpl());
    }

    @Override
    public CategoryRepository createCategoryRepositoryFile(String dbFileNAme) {
        return new CategoryRepositoryFileImpl(new IdGeneratorImpl(), dbFileNAme);
    }

    @Override
    public GameRepositoryMemoryImpl createGameRepository() {
        return new GameRepositoryMemoryImpl(new IdGeneratorImpl());
    }

    @Override
    public GameRepository createGameRepositoryFile(String dbFileNAme) {
        return new GameRepositoryFileImpl(new IdGeneratorImpl(), dbFileNAme);
    }
}
