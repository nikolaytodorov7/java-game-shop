package ogs.util;

import ogs.exception.ConstraintViolation;
import ogs.exception.ConstraintViolationException;
import ogs.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameValidator {
    public void validate(Game game) throws ConstraintViolationException {
        List<ConstraintViolation> violationList = new ArrayList<>();
        var titleLength = game.getTitle().trim().length();
        var descriptionLength = game.getDescription().trim().length();

        if (titleLength < 2 || titleLength > 50)
            violationList.add(
                    new ConstraintViolation(game.getClass().getName(), "title", game.getTitle(),
                            "Title length must be between 2 and 50 characters long."));

        if (descriptionLength < 20 || descriptionLength > 250)
            violationList.add(
                    new ConstraintViolation(game.getClass().getName(), "description", game.getDescription(),
                            "Description length must be between 20 and 250 characters long."));

        if (game.getPrice() <= 0)
            violationList.add(
                    new ConstraintViolation(game.getClass().getName(), "price", game.getPrice(),
                            "Price must be more than 0."));

        if (violationList.size() > 0) {
            throw new ConstraintViolationException("Invalid game field/fields", violationList);
        }
    }
}
