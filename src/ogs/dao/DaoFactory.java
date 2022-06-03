package ogs.dao;

public interface DaoFactory {
    UserRepository createUserRepository();

    public UserRepository createUserRepositoryFile(String dbFileNAme);

    CategoryRepository createCategoryRepository();

    public CategoryRepository createCategoryRepositoryFile(String dbFileNAme);


    GameRepository createGameRepository();

    public GameRepository createGameRepositoryFile(String dbFileNAme);
}
