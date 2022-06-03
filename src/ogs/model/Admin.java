package ogs.model;

import java.util.StringJoiner;

public class Admin extends User {

    public Admin() {
    }

    public Admin(String firstName, String lastName,
                 String password) {
        super(firstName, lastName, password);
    }

    public Admin(String firstName, String lastName, String email, String username,
                 String password, Gender gender, Status status) {
        super(firstName, lastName, email, username, password, gender, Role.ADMIN, status);
    }

    @Override
    public String toString() {
        return new StringJoiner(" | ", "", "")
                .add(super.toString())
                .toString();
    }
}
