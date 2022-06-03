package ogs.view;

import ogs.exception.NonExistingEntityException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerDialog {
    public static Scanner scanner = new Scanner(System.in);
    String nameRegex = "^[A-za-z]{2,15}$";
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[?!@#$%^&*()])[A-Za-z\\d?!@#$%^&*()]{8,15}$";

    public String getGameTitle() {
        System.out.println("Enter Game Title: ");
        var gameTitle = scanner.nextLine();
        if (gameTitle.length() < 2 || gameTitle.length() > 15) {
            System.out.println("Error: Title length must be between 2 and 15 characters long.");
            return null;
        } else
            return gameTitle;
    }

    public double getMoney() {
        System.out.println("Enter Money to deposit (150 = 1.50 dollar): ");
        double money = 0;
        try {
            money = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        if (money <= 0) {
            System.out.println("Error: Money should be positive value.");
            return 0;
        } else {
            System.out.printf("Money deposited: $%.2f.", money / 100);
            return money;
        }
    }

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
}
