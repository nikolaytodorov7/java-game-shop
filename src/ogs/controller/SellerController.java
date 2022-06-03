package ogs.controller;

import ogs.exception.NonExistingEntityException;
import ogs.model.Game;
import ogs.model.User;
import ogs.model.Seller;
import ogs.service.UserService;
import ogs.service.CategoryService;
import ogs.service.GameService;
import ogs.view.SellerDialog;
import ogs.view.Menu;

import java.util.List;

public class SellerController {
    private UserService userService;
    private CategoryService categoryService;
    private GameService gameService;

    public SellerController(UserService userService, CategoryService categoryService, GameService gameService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.gameService = gameService;
    }

    public void init(Seller seller) {
        userService.loadData();
        categoryService.loadData();
        gameService.loadData();

        var menu = new Menu("Seller Menu", List.of(
                new Menu.Option("Browse Games", () -> {
                    var games = gameService.getAllGames();
                    System.out.println("All Games are: ");
                    games.forEach(System.out::println);
                    return "Total Games count: " + games.size();
                }),

                new Menu.Option("Publish Game", () -> {
                    System.out.println("All Categories are:");
                    categoryService.getAllCategories().forEach(System.out::println);
                    System.out.println("Enter Game Data");
                    var game = new SellerDialog().createGame(seller, categoryService);
                    gameService.addGame(game);
                    return "Game added successfully.";
                }),

                new Menu.Option("Edit Game", () -> {
                    var title = new SellerDialog().getGameTitle();
                    if (title == null)
                        return "Error: Title length must be between 2 and 50 characters.";
                    try {
                        if (gameService.getGameByTitle(title).getSeller().equals(seller))
                            return "Error: This game is not published by you.";
                        else
                            updateGame(gameService.getGameByTitle(title));
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    return "Game Data updated successfully.";
                }),

                new Menu.Option("Show User Data", () -> String.format("User Data: is %s", seller)),

                new Menu.Option("Update User Data", () -> {
                    updateUser(seller);
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
                    var firstName = new SellerDialog().getFirstName();
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
                    var lastName = new SellerDialog().getLastName();
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
                    var password = new SellerDialog().getPassword();
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

    public void updateGame(Game game) {
        var menu = new Menu("Game Data Menu", List.of(
                new Menu.Option("Edit Title", () -> {
                    var title = new SellerDialog().getGameTitle();
                    if (title == null)
                        return "Error: Title updating failed.";
                    else {
                        game.setTitle(title);
                        try {
                            gameService.updateGame(game);
                        } catch (NonExistingEntityException e) {
                            return e.getMessage();
                        }
                        return "Title updated successfully.";
                    }
                }),

                new Menu.Option("Edit Description", () -> {
                    var description = new SellerDialog().getGameDescription();
                    if (description == null)
                        return "Error: Description updating failed.";
                    else {
                        game.setDescription(description);
                        try {
                            gameService.updateGame(game);
                        } catch (NonExistingEntityException e) {
                            return e.getMessage();
                        }
                        return "Description updated successfully.";
                    }
                }),

                new Menu.Option("Edit Price", () -> {
                    var price = new SellerDialog().getGamePrice();
                    if (price == 0)
                        return "Error: Price must be more than 0.";
                    else {
                        game.setPrice(price);
                        return "Price updated successfully.";
                    }
                }),

                new Menu.Option("Add Promotion", () -> {
                    var promo = new SellerDialog().getGamePromo();
                    if (promo == 0)
                        return "Error: Promotion must be more than 0%.";
                    gameService.gamePromotion(game, promo);
                    return "Game promo added successfully.";
                })
        ));

        menu.show();
        gameService.saveData();
    }
}
