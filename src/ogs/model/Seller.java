package ogs.model;

import java.util.StringJoiner;

public class Seller extends User {
    private Type type;
    private double balance;

    public Seller() {
    }

    public Seller(String firstName, String lastName, String email, String username,
                  String password, Gender gender, Status status,
                  Type type, double balance) {
        super(firstName, lastName, email, username, password, gender, Role.SELLER, status);
        this.type = type;
        this.balance = balance;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
                .add("type=" + type)
                .add(String.format("Budget= $%.2f", balance / 100))
                .toString();
    }
}
