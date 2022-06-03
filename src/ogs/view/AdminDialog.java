package ogs.view;

import ogs.model.*;

import java.util.Scanner;

public class AdminDialog {
    public static Scanner scanner = new Scanner(System.in);
    String nameRegex = "^[A-za-z]{2,15}$";
    String emailRegex = "^(.+)@(.+)$";
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[?!@#$%^&*()])[A-Za-z\\d?!@#$%^&*()]{8,15}$";

    public Customer createCustomer() {
        var customer = new Customer();

        while (customer.getFirstName() == null) {
            System.out.println("First Name:");
            var firstName = scanner.nextLine();
            if (!firstName.matches(nameRegex))
                System.out.println("Error: First Name length must be between 2 and 15 word characters.");
            else
                customer.setFirstName(firstName);
        }

        while (customer.getLastName() == null) {
            System.out.println("Last Name:");
            var lastName = scanner.nextLine();
            if (!lastName.matches(nameRegex))
                System.out.println("Error: Last Name length must be between 2 and 15 word characters.");
            else
                customer.setLastName(lastName);
        }

        while (customer.getEmail() == null) {
            System.out.println("Email:");
            var email = scanner.nextLine();
            if (!email.matches(emailRegex))
                System.out.println("Error: Email must be valid.");
            else
                customer.setEmail(email);
        }

        while (customer.getUsername() == null) {
            System.out.println("Username:");
            var username = scanner.nextLine();
            if (!username.matches(nameRegex))
                System.out.println("Error: Username length must be between 2 and 15 word characters.");
            else
                customer.setUsername(username);
        }

        while (customer.getPassword() == null) {
            System.out.println("Password:");
            var password = scanner.nextLine();
            if (!password.matches(passwordRegex))
                System.out.println("Error: Password must contain at least one digit, one capital letter, one symbol " +
                        "and length must be between 8 and 15 characters.");
            else
                customer.setPassword(password);
        }

        while (customer.getGender() == null) {
            System.out.println("Gender:");
            var gender = scanner.nextLine().toUpperCase();
            if (!gender.equals(Gender.MALE.name()) && !gender.equals(Gender.FEMALE.name()))
                System.out.println("Error: Gender must be MALE or FEMALE.");
            else
                customer.setGender(Gender.valueOf(gender));
        }

        customer.setStatus(Status.ACTIVE);
        customer.setRole(Role.CUSTOMER);
        customer.setBalance(0);
        return customer;
    }

    public Seller createSeller() {
        var seller = new Seller();

        while (seller.getFirstName() == null) {
            System.out.println("First Name:");
            var firstName = scanner.nextLine();
            if (!firstName.matches(nameRegex))
                System.out.println("Error: First Name length must be between 2 and 15 word characters.");
            else
                seller.setFirstName(firstName);
        }

        while (seller.getLastName() == null) {
            System.out.println("Last Name:");
            var lastName = scanner.nextLine();
            if (!lastName.matches(nameRegex))
                System.out.println("Error: Last Name length must be between 2 and 15 word characters.");
            else
                seller.setLastName(lastName);
        }

        while (seller.getEmail() == null) {
            System.out.println("Email:");
            var email = scanner.nextLine();
            if (!email.matches(emailRegex))
                System.out.println("Error: Email must be valid.");
            else
                seller.setEmail(email);
        }

        while (seller.getUsername() == null) {
            System.out.println("Username:");
            var username = scanner.nextLine();
            if (!username.matches(nameRegex))
                System.out.println("Error: Username length must be between 2 and 15 word characters.");
            else
                seller.setUsername(username);
        }

        while (seller.getPassword() == null) {
            System.out.println("Password:");
            var password = scanner.nextLine();
            if (!password.matches(passwordRegex))
                System.out.println("Error: Password must contain at least one digit, one capital letter, one symbol " +
                        "and length must be between 8 and 15 characters.");
            else
                seller.setPassword(password);
        }

        while (seller.getGender() == null) {
            System.out.println("Gender:");
            var gender = scanner.nextLine().toUpperCase();
            if (!gender.equals(Gender.MALE.name()) && !gender.equals(Gender.FEMALE.name()))
                System.out.println("Error: Gender must be MALE or FEMALE.");
            else
                seller.setGender(Gender.valueOf(gender));
        }

        while (seller.getType() == null) {
            System.out.println("Type");
            var type = scanner.nextLine().toUpperCase();
            if (!type.equals(Type.PERSON.name()) && !type.equals(Type.CORPORATE.name()))
                System.out.println("Error: Type must be PERSON or CORPORATE.");
            else
                seller.setType(Type.valueOf(type));
        }

        seller.setStatus(Status.ACTIVE);
        seller.setRole(Role.SELLER);
        seller.setBalance(0);
        return seller;
    }

    public Admin createAdmin() {
        var admin = new Admin();

        while (admin.getFirstName() == null) {
            System.out.println("First Name:");
            var firstName = scanner.nextLine();
            if (!firstName.matches(nameRegex))
                System.out.println("Error: First Name length must be between 2 and 15 word characters.");
            else
                admin.setFirstName(firstName);
        }

        while (admin.getLastName() == null) {
            System.out.println("Last Name:");
            var lastName = scanner.nextLine();
            if (!lastName.matches(nameRegex))
                System.out.println("Error: Last Name length must be between 2 and 15 word characters.");
            else
                admin.setLastName(lastName);
        }

        while (admin.getEmail() == null) {
            System.out.println("Email:");
            var email = scanner.nextLine();
            if (!email.matches(emailRegex))
                System.out.println("Error: Email must be valid.");
            else
                admin.setEmail(email);
        }

        while (admin.getUsername() == null) {
            System.out.println("Username:");
            var username = scanner.nextLine();
            if (!username.matches(nameRegex))
                System.out.println("Error: Username length must be between 2 and 15 word characters.");
            else
                admin.setUsername(username);
        }

        while (admin.getPassword() == null) {
            System.out.println("Password:");
            var password = scanner.nextLine();
            if (!password.matches(passwordRegex))
                System.out.println("Error: Password must contain at least one digit, one capital letter, one symbol " +
                        "and length must be between 8 and 15 characters.");
            else
                admin.setPassword(password);
        }

        while (admin.getGender() == null) {
            System.out.println("Gender:");
            var gender = scanner.nextLine().toUpperCase();
            if (!gender.equals(Gender.MALE.name()) && !gender.equals(Gender.FEMALE.name()))
                System.out.println("Error: Gender must be MALE or FEMALE.");
            else
                admin.setGender(Gender.valueOf(gender));
        }

        admin.setStatus(Status.ACTIVE);
        admin.setRole(Role.ADMIN);
        return admin;
    }

    public Category createCategory() {
        var category = new Category();

        while (category.getTitle() == null) {
            System.out.println("Title:");
            var title = scanner.nextLine();
            if (title.length() < 2 || title.length() > 15)
                System.out.println("Error: Title length must be between 2 and 15 characters long.");
            else
                category.setTitle(title);
        }

        while (category.getDescription() == null) {
            System.out.println("Description:");
            var description = scanner.nextLine();
            if (description.length() < 10 || description.length() > 500)
                System.out.println("Error: Description length must be between 10 and 500 characters long.");
            else
                category.setDescription(description);
        }
        return category;
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

    public String getUsername() {
        System.out.println("Username: ");
        var username = scanner.nextLine();
        if (!username.matches(nameRegex)) {
            return null;
        } else
            return username;
    }

    public String getGameTitle() {
        System.out.println("Game Title: ");
        var gameTitle = scanner.nextLine();
        if (gameTitle.length() < 2 || gameTitle.length() > 50) {
            System.out.println("Error: Title length must be between 2 and 50 characters long.");
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

    public String getCategoryTitle() {
        System.out.println("Category Title: ");
        var title = scanner.nextLine();
        if (title.length() < 2 || title.length() > 15) {
            System.out.println("Error: Title length must be between 2 and 15 characters long.");
            return null;
        } else
            return title;
    }

    public String getCategoryDescription() {
        System.out.println("Category Description: ");
        var description = scanner.nextLine();
        if (description.length() < 10 || description.length() > 100) {
            System.out.println("Error: Description length must be between 10 and 100 characters long.");
            return null;
        } else
            return description;
    }
}
