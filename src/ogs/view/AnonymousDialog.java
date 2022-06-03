package ogs.view;

import ogs.model.Customer;
import ogs.model.Gender;
import ogs.model.Role;
import ogs.model.Status;

import java.util.Scanner;

public class AnonymousDialog {
    public static Scanner scanner = new Scanner(System.in);
    String nameRegex = "^[A-za-z]{1,15}$";
    String emailRegex = "^(.+)@(.+)$";
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[?!@#$%^&*()])[A-Za-z\\d?!@#$%^&*()]{8,15}$";

    public Customer register() {
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
                        "and length must     be between 8 and 15 characters.");
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

    public String getUsername() {
        System.out.println("Username: ");
        var username = scanner.nextLine();
        if (username.equals("")) {
            System.out.println("Error: You must enter a username.");
            return null;
        } else
            return username.toLowerCase();
    }

    public String getPassword() {
        System.out.println("Password: ");
        var password = scanner.nextLine();
        if (password.equals("")) {
            System.out.println("Error: You must enter a password.");
            return null;
        } else
            return password;
    }
}
