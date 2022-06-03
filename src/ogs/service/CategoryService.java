package ogs.service;

import ogs.exception.InvalidEntityDataException;
import ogs.exception.NonExistingEntityException;
import ogs.model.Category;
import ogs.model.Game;
import ogs.model.User;

import java.util.Collection;

public interface CategoryService {
    void loadData();

    void saveData();

    Collection<Category> getAllCategories();

    Category getCategoryById(Long id) throws NonExistingEntityException;

    Category addCategory(Category category) throws InvalidEntityDataException;

    Category updateCategory(Category category) throws NonExistingEntityException, InvalidEntityDataException;

    Category deleteCategoryById(Long id) throws NonExistingEntityException;

    long count();

    Category getCategoryByTitle(String title) throws NonExistingEntityException;

    Category deleteCategoryByTitle(String title) throws NonExistingEntityException;
}
