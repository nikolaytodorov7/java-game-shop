package ogs.util;

import ogs.exception.ConstraintViolation;
import ogs.exception.ConstraintViolationException;
import ogs.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {
    public void validate(Category category) throws ConstraintViolationException {
        List<ConstraintViolation> violationList = new ArrayList<>();
        var titleLength = category.getTitle().trim().length();
        var descriptionLength = category.getDescription().trim().length();

        if (titleLength < 2 || titleLength > 15)
            violationList.add(
                    new ConstraintViolation(category.getClass().getName(), "title", category.getTitle(),
                            "Title length must be between 2 and 15 characters long."));

        if (descriptionLength < 10 || descriptionLength > 100)
            violationList.add(
                    new ConstraintViolation(category.getClass().getName(), "description", category.getDescription(),
                            "Description length must be between 10 and 100 characters long."));

        if (violationList.size() > 0) {
            throw new ConstraintViolationException("Invalid category field/fields", violationList);
        }
    }
}
