package ogs.util;

import ogs.exception.ConstraintViolation;
import ogs.exception.ConstraintViolationException;
import ogs.model.Status;
import ogs.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public void validate(User user) throws ConstraintViolationException {
        List<ConstraintViolation> violationList = new ArrayList<>();
        var nameRegex = "^[A-za-z]{1,15}$";
        var emailRegex = "^(.+)@(.+)$";
        var passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[?!@#$%^&*()])[A-Za-z\\d?!@#$%^&*()]{8,15}$";

        if (!user.getFirstName().matches(nameRegex))
            violationList.add(
                    new ConstraintViolation(user.getClass().getName(), "firstName", user.getFirstName(),
                            "First name length must be between 2 and 15 word characters."));

        if (!user.getLastName().matches(nameRegex))
            violationList.add(
                    new ConstraintViolation(user.getClass().getName(), "lastName", user.getLastName(),
                            "Last name length must be between 2 and 15 word characters."));

        if (!user.getEmail().matches(emailRegex))
            violationList.add(
                    new ConstraintViolation(user.getClass().getName(), "email", user.getEmail(),
                            "Email must be valid."));

        if (!user.getUsername().matches(nameRegex))
            violationList.add(
                    new ConstraintViolation(user.getClass().getName(), "username", user.getUsername(),
                            "Username length must be between 2 and 15 word characters."));

        if (!user.getPassword().matches(passwordRegex))
            violationList.add(
                    new ConstraintViolation(user.getClass().getName(), "password", user.getPassword(),
                            "Password must contain at least one digit, one capital letter, one symbol " +
                                    "and length should be between 8 and 15 characters."));

        if (user.getStatus().equals(Status.DEACTIVATED))
            violationList.add(new ConstraintViolation(user.getClass().getName(), "status", user.getStatus(),
                    "Your account is permanently banned."));

        if (violationList.size() > 0) {
            throw new ConstraintViolationException("Invalid user field/fields", violationList);
        }
    }
}