package ogs.model;

import java.util.StringJoiner;

public class Customer extends User {
    private double balance;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email,
                    String username, String password, Gender gender,
                    Status status, double balance) {
        super(firstName, lastName, email, username, password, gender, Role.CUSTOMER, status);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return new StringJoiner(" | ", "", "")
                .add(super.toString())
                .add(String.format("Budget= $%.2f", balance / 100))
                .toString();
    }
}
