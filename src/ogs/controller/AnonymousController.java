package ogs.controller;

import ogs.exception.NonExistingEntityException;
import ogs.model.*;
import ogs.service.UserService;
import ogs.service.CategoryService;
import ogs.service.GameService;
import ogs.view.AnonymousDialog;
import ogs.view.Menu;

import java.util.List;

public class AnonymousController {
    private UserService userService;
    private CategoryService categoryService;
    private GameService gameService;

    public AnonymousController(UserService userService, CategoryService categoryService, GameService gameService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.gameService = gameService;
    }

    public void init() {
        userService.loadData();
        categoryService.loadData();
        gameService.loadData();

        var menu = new Menu("Main Menu", List.of(
                new Menu.Option("Browse Games", () -> {
                    var games = gameService.getAllGames();
                    games.forEach(System.out::println);
                    return "Total Games count: " + games.size();
                }),

                new Menu.Option("Log In", () -> {
                    User user = null;
                    var username = new AnonymousDialog().getUsername();
                    if (username == null)
                        return "Try again.";
                    try {
                        user = userService.getUserByUsername(username);
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    if (user.getId() == null)
                        return String.format("User with username '%s' does not exist.", username);
                    var password = new AnonymousDialog().getPassword();
                    if (password == null)
                        return "Try again.";
                    else if (password.equals(user.getPassword()) && user.getRole().equals(Role.CUSTOMER)) {
                        var customerController = new CustomerController(userService, categoryService, gameService);
                        customerController.init((Customer) user);
                    } else if (password.equals(user.getPassword()) && user.getRole().equals(Role.SELLER)) {
                        var sellerController = new SellerController(userService, categoryService, gameService);
                        sellerController.init((Seller) user);
                    } else if (password.equals(user.getPassword()) && user.getRole().equals(Role.ADMIN)) {
                        var adminController = new AdminController(userService, categoryService, gameService);
                        adminController.init((Admin) user);
                    } else
                        System.out.println("Invalid password.");
                    return "";
                }),

                new Menu.Option("Register", () -> {
                    System.out.println("Enter User Data");
                    var newUser = new AnonymousDialog().register();
                    userService.addUser(newUser);
                    return " ";
                })
        ));
        userService.saveData();
        menu.show();
    }
}
