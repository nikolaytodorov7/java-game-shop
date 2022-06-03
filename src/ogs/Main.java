package ogs;

import ogs.controller.AnonymousController;
import ogs.dao.CategoryRepository;
import ogs.dao.DaoFactory;
import ogs.dao.GameRepository;
import ogs.dao.UserRepository;
import ogs.dao.impl.DaoFactoryImpl;
import ogs.service.CategoryService;
import ogs.service.GameService;
import ogs.service.UserService;
import ogs.service.impl.CategoryServiceImpl;
import ogs.service.impl.GameServiceImpl;
import ogs.service.impl.UserServiceImpl;

/**
 * Users to test different roles. If you register, default role will be customer.
 *  Seller username: vanko, password vankata00R!
 *  Customer username: petranka, password petrana123!X
 *  Admin username: niki, password nikiTod!99
 */

public class Main {
    public static final String USERS_DB_FILENAME = "users.db";
    public static final String CATEGORIES_DB_FILENAME = "categories.db";
    public static final String GAMES_DB_FILENAME = "games.db";

    public static void main(String[] args) {
        DaoFactory daoFactory = new DaoFactoryImpl();
        UserRepository userRepository = daoFactory.createUserRepositoryFile(USERS_DB_FILENAME);
        UserService userService = new UserServiceImpl(userRepository);
        CategoryRepository categoryRepository = daoFactory.createCategoryRepositoryFile(CATEGORIES_DB_FILENAME);
        CategoryService categoryService = new CategoryServiceImpl(categoryRepository);
        GameRepository gameRepository = daoFactory.createGameRepositoryFile(GAMES_DB_FILENAME);
        GameService gameService = new GameServiceImpl(gameRepository);

        userService.loadData();
        gameService.loadData();
        categoryService.loadData();

//        userService.getAllUsers().forEach(System.out::println);


//        gameService.getAllGames().forEach(System.out::println);

        var anonymousController = new AnonymousController(userService,categoryService,gameService);
        anonymousController.init();

        userService.saveData();
        categoryService.saveData();
        gameService.saveData();
    }
}