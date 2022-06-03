package ogs.view;

import ogs.exception.InvalidEntityDataException;
import ogs.exception.NonExistingEntityException;
import ogs.model.Category;
import ogs.model.Game;
import ogs.model.Seller;
import ogs.service.CategoryService;
import ogs.service.GameService;

import java.util.Scanner;

public class SellerDialog {
    public static Scanner scanner = new Scanner(System.in);
    String nameRegex = "^[A-za-z]{2,15}$";
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[?!@#$%^&*()])[A-Za-z\\d?!@#$%^&*()]{8,15}$";

    public String getFirstName() {
        System.out.println("First Name: ");
        var firstName = scanner.nextLine();
        if (!firstName.matches(nameRegex)) {
            System.out.println("Error: First Name length must be between 2 and 15 word characters.");
            return null;
        } else
            return firstName;
    }

    public String getLastName() {
        System.out.println("Last Name: ");
        var lastName = scanner.nextLine();
        if (!lastName.matches(nameRegex)) {
            System.out.println("Error: Last Name length must be between 2 and 15 word characters.");
            return null;
        } else
            return lastName;
    }

    public String getPassword() {
        System.out.println("Password: ");
        var password = scanner.nextLine();
        if (!password.matches(passwordRegex)) {
            System.out.println("Error: Password must contain at least one digit, one capital letter, " +
                    "one symbol and length must be between 8 and 15 characters.");
            return null;
        } else
            return password;
    }

    public Game createGame(Seller seller, CategoryService categoryService) {
        var game = new Game();

        while (game.getCategory() == null) {
            Category category = null;
            System.out.println("Category Title:");
            var title = scanner.nextLine();
            if (title.length() < 2 || title.length() > 15) {
                System.out.println("Error: Category Title length must be between 2 and 15 characters long.");
            } else {
                try {
                    category = categoryService.getCategoryByTitle(title);
                } catch (NonExistingEntityException e) {
                    System.out.println(e.getMessage());
                }
                game.setCategory(category);
            }
        }

        while (game.getTitle() == null) {
            System.out.println("Title:");
            var title = scanner.nextLine();
            if (title.length() < 2 || title.length() > 50)
                System.out.println("Error: Title length must be between 2 and 50 characters long.");
            else
                game.setTitle(title);
        }

        while (game.getDescription() == null) {
            System.out.println("Description:");
            var desc = scanner.nextLine();
            if (desc.length() < 20 || desc.length() > 250)
                System.out.println("Error: Description length must be between 20 and 250 characters long.");
            else
                game.setDescription(desc);
        }

        while (game.getPrice() == 0) {
            System.out.println("Game Price:");
            var price = scanner.nextDouble();
            if (price <= 0)
                System.out.println("Error: Price must be more than 0.");
            else
                game.setPrice(price);
        }

        game.setSeller(seller);
        return game;
    }

    public String getGameTitle() {
        System.out.println("Game Title: ");
        var gameTitle = scanner.nextLine();
        if (gameTitle.length() < 2 || gameTitle.length() > 50) {
            return null;
        } else
            return gameTitle;
    }

    public String getGameDescription() {
        System.out.println("Game Description: ");
        var gameDescription = scanner.nextLine();
        if (gameDescription.length() < 20 || gameDescription.length() > 250) {
            System.out.println("Error: Description length must be between 20 and 250 characters long.");
            return null;
        } else
            return gameDescription;
    }

    public double getGamePrice() {
        System.out.println("Game Price: ");
        var gamePrice = scanner.nextDouble();
        if (gamePrice <= 0) {
            System.out.println("Error: Price must be more than 0.");
            return 0;
        } else
            return gamePrice;
    }

    public double getGamePromo() {
        System.out.println("Game Promo %: ");
        var promo = scanner.nextDouble();
        if (promo <= 0) {
            System.out.println("Error: Promotion percentage must be more than 0.");
            return 0;
        } else
            return promo;
    }
}
