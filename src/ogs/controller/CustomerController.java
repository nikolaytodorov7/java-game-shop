package ogs.controller;

import ogs.exception.NonExistingEntityException;
import ogs.model.Game;
import ogs.model.User;
import ogs.model.Customer;
import ogs.service.UserService;
import ogs.service.CategoryService;
import ogs.service.GameService;
import ogs.view.CustomerDialog;
import ogs.view.Menu;

import java.util.List;

public class CustomerController {
    private UserService userService;
    private CategoryService categoryService;
    private GameService gameService;

    public CustomerController(UserService userService, CategoryService categoryService, GameService gameService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.gameService = gameService;
    }

    public void init(Customer customer) {
        userService.loadData();
        categoryService.loadData();
        gameService.loadData();

        var menu = new Menu("Customer Menu", List.of(
                new Menu.Option("Browse Games", () -> {
                    var games = gameService.getAllGames();
                    System.out.println("All Games are: ");
                    games.forEach(System.out::println);
                    return "Total Games count: " + games.size();
                }),

                new Menu.Option("Buy Game", () -> {
                    gameService.getAllGames().forEach(System.out::println);
                    var gameTitle = new CustomerDialog().getGameTitle();
                    if (gameTitle == null)
                        return "";
                    Game game;
                    try {
                        game = gameService.getGameByTitle(gameTitle);
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    userService.buyGame(customer, game);
                    try {
                        if (game != null) {
                            userService.updateUser(customer);
                            userService.updateUser(userService.getUserByUsername(game.getSeller().getUsername()));
                        }
                        else
                            return "";
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    return String.format("Game '%s' bought.", game.getTitle());
                }),

                new Menu.Option("Deposit Money", () -> {
                    var money = new CustomerDialog().getMoney();
                    if (money == 0)
                        return "Error: Invalid Money.";
                    else {
                        userService.depositMoney(customer, money);
                        try {
                            userService.updateUser(customer);
                        } catch (NonExistingEntityException e) {
                            System.out.println(e.getMessage());
                        }
                        return String.format(" Current Balance: $%.2f", customer.getBalance() / 100);
                    }
                }),

                new Menu.Option("Show User Data", () -> String.format("User Data: %s", customer)),

                new Menu.Option("Update User Data", () -> {
                    updateUser(customer);
                    return "User Data updated successfully.";
                })
        ));

        menu.show();
        userService.saveData();
        categoryService.saveData();
        gameService.saveData();
    }

    public void updateUser(User user) {
        var menu = new Menu("Edit User Data Menu", List.of(
                new Menu.Option("Enter First Name", () -> {
                    var firstName = new CustomerDialog().getFirstName();
                    if (firstName == null)
                        return "Error: First Name updating failed.";
                    else {
                        user.setFirstName(firstName);
                        try {
                            userService.updateUser(user);
                        } catch (NonExistingEntityException e) {
                            return e.getMessage();
                        }
                        return "First Name updated successfully.";
                    }
                }),

                new Menu.Option("Enter Last Name", () -> {
                    var lastName = new CustomerDialog().getLastName();
                    if (lastName == null)
                        return "Error: Last Name updating failed.";
                    else {
                        user.setLastName(lastName);
                        try {
                            userService.updateUser(user);
                        } catch (NonExistingEntityException e) {
                            return e.getMessage();
                        }
                        return "Last Name updated successfully.";
                    }
                }),

                new Menu.Option("Enter Password: ", () -> {
                    var password = new CustomerDialog().getPassword();
                    if (password == null)
                        return "Error: Password updating failed.";
                    else {
                        user.setPassword(password);
                        try {
                            userService.updateUser(user);
                        } catch (NonExistingEntityException e) {
                            return e.getMessage();
                        }
                        return "Password updated successfully.";
                    }
                })
        ));

        menu.show();
        userService.saveData();
    }
}