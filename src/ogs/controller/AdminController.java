package ogs.controller;

import ogs.exception.NonExistingEntityException;
import ogs.model.*;
import ogs.service.CategoryService;
import ogs.service.GameService;
import ogs.service.UserService;
import ogs.view.AdminDialog;
import ogs.view.Menu;
import ogs.view.SellerDialog;

import java.util.List;

public class AdminController {
    private UserService userService;
    private CategoryService categoryService;
    private GameService gameService;

    public AdminController(UserService userService, CategoryService categoryService, GameService gameService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.gameService = gameService;
    }

    public void init(Admin admin) {
        userService.loadData();
        categoryService.loadData();
        gameService.loadData();

        var menu = new Menu("Admin Menu", List.of(

                new Menu.Option("User Management", () -> {
                    manageUser();
                    return "";
                }),

                new Menu.Option("Category Management", () -> {
                    manageCategory();
                    return "";
                }),

                new Menu.Option("Game Management", () -> {
                    manageGame();
                    return "";
                }),

                new Menu.Option("Show User Data", () -> String.format("User Data: %s", admin)),

                new Menu.Option("Update User Data", () -> {
                    updateUser(admin);
                    return "User Data updated successfully.";
                })
        ));

        menu.show();
        userService.saveData();
        categoryService.saveData();
        gameService.saveData();
    }

    public void manageUser() {
        var menu = new Menu("Manage Users", List.of(
                new Menu.Option("Show All Users", () -> {
                    var users = userService.getAllUsers();
                    users.forEach(System.out::println);
                    return "Total Users count: " + users.size();
                }),

                new Menu.Option("Create Customer", () -> {
                    System.out.println("Enter Customer Data");
                    var customer = new AdminDialog().createCustomer();
                    userService.addUser(customer);
                    return "Customer created successfully.";
                }),

                new Menu.Option("Create Seller", () -> {
                    System.out.println("Enter Seller Data");
                    var seller = new AdminDialog().createSeller();
                    userService.addUser(seller);
                    return "Seller created successfully.";
                }),

                new Menu.Option("Create Admin", () -> {
                    System.out.println("Enter Admin Data");
                    var admin = new AdminDialog().createAdmin();
                    userService.addUser(admin);
                    return "Admin created successfully.";
                }),

                new Menu.Option("Edit User", () -> {
                    System.out.println("Enter Username:");
                    var username = new AdminDialog().getUsername();
                    if (username == null)
                        return "Error: Username length must be between 2 and 15 word characters.";
                    try {
                        updateUser(userService.getUserByUsername(username));
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    return "User Data updated successfully.";
                }),

                new Menu.Option("Delete User", () -> {
                    System.out.println("Enter Username:");
                    var username = new AdminDialog().getUsername();
                    if (username == null)
                        return "Error: Username length must be between 2 and 15 word characters.";
                    try {
                        userService.deleteUserByUsername(username);
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    return "User deleted successfully";
                })
        ));

        menu.show();
        userService.saveData();
        categoryService.saveData();
        gameService.saveData();
    }

    public void manageCategory() {
        var menu = new Menu("Manage Categories", List.of(
                new Menu.Option("Show All Categories", () -> {
                    var categories = categoryService.getAllCategories();
                    categories.forEach(System.out::println);
                    return "Total Categories count: " + categories.size();
                }),

                new Menu.Option("Add Category", () -> {
                    System.out.println("Enter Category Data");
                    var category = new AdminDialog().createCategory();
                    categoryService.addCategory(category);
                    return String.format("Category created successfully");
                }),

                new Menu.Option("Edit Category", () -> {
                    System.out.println("Enter Title:");
                    var title = new AdminDialog().getCategoryTitle();
                    if (title == null)
                        return "Error: Title length must be between 2 and 15 characters.";
                    try {
                        updateCategory(categoryService.getCategoryByTitle(title));
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    return "Category Data updated successfully.";
                }),

                new Menu.Option("Delete Category", () -> {
                    System.out.println("Enter Title:");
                    var title = new AdminDialog().getCategoryTitle();
                    if (title == null)
                        return "Error: Title length must be between 2 and 15 characters.";
                    try {
                        categoryService.deleteCategoryByTitle(title);
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    return "Category deleted successfully";
                })
        ));

        menu.show();
        categoryService.saveData();
    }

    public void manageGame() {
        var menu = new Menu("Manage Games", List.of(
                new Menu.Option("Show All Games", () -> {
                    var games = gameService.getAllGames();
                    games.forEach(System.out::println);
                    return "Total Games count: " + games.size();
                }),

                new Menu.Option("Edit Game", () -> {
                    System.out.println("Enter Title:");
                    var title = new AdminDialog().getGameTitle();
                    if (title == null)
                        return "Error: Title length must be between 2 and 15 characters.";
                    try {
                        updateGame(gameService.getGameByTitle(title));
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    return "Game Data updated successfully.";
                }),

                new Menu.Option("Delete Game", () -> {
                    System.out.println("Enter Title:");
                    var title = new AdminDialog().getGameTitle();
                    if (title == null)
                        return "Error: Title length must be between 2 and 15 characters.";
                    try {
                        gameService.deleteGameByTitle(title);
                    } catch (NonExistingEntityException e) {
                        return e.getMessage();
                    }
                    return "Game deleted successfully";
                })
        ));

        menu.show();
        gameService.saveData();
    }

    public void updateUser(User user) {
        var menu = new Menu("Edit User Data Menu", List.of(
                new Menu.Option("Enter First Name", () -> {
                    var firstName = new AdminDialog().getFirstName();
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
                    var lastName = new AdminDialog().getLastName();
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
                    var password = new AdminDialog().getPassword();
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

    public void updateCategory(Category category) {
        var menu = new Menu("Category Data Menu", List.of(
                new Menu.Option("Edit Title", () -> {
                    var title = new AdminDialog().getCategoryTitle();
                    if (title == null)
                        return "Error: Title updating failed.";
                    else {
                        category.setTitle(title);
                        try {
                            categoryService.updateCategory(category);
                        } catch (NonExistingEntityException e) {
                            return e.getMessage();
                        }
                        return "Title updated successfully.";
                    }
                }),

                new Menu.Option("Edit Description", () -> {
                    var description = new AdminDialog().getCategoryDescription();
                    if (description == null)
                        return "Error: Description updating failed.";
                    else {
                        category.setDescription(description);
                        try {
                            categoryService.updateCategory(category);
                        } catch (NonExistingEntityException e) {
                            return e.getMessage();
                        }
                        return "Description updated successfully.";
                    }
                })
        ));

        menu.show();
        categoryService.saveData();
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