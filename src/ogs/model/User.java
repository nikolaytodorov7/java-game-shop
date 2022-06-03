package ogs.model;

import ogs.dao.Identifiable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.StringJoiner;

public class User implements Identifiable<Long>, Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Gender gender;
    private Role role;
    private Status status;
    private Timestamp created;
    private Timestamp modified;

    public User() {
    }

    public User(String firstName, String lastName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String username, String password,
                Gender gender, Role role, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email.toLowerCase();
        this.username = username.toLowerCase();
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(" | ", "", "")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("email='" + email + "'")
                .add("username='" + username + "'")
//                Hidden in order to not show user passwords when searching for users.
//                .add("password='" + password + "'")
                .add("gender=" + gender)
                .add("role=" + role)
                .add("status=" + status)
                .add("created=" + created)
                .add("modified=" + modified)
                .toString();
    }
}
