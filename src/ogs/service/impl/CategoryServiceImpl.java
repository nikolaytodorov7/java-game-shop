package ogs.service.impl;

import ogs.dao.CategoryRepository;
import ogs.exception.ConstraintViolationException;
import ogs.exception.InvalidEntityDataException;
import ogs.exception.NonExistingEntityException;
import ogs.model.Category;
import ogs.service.CategoryService;
import ogs.util.CategoryValidator;

import java.sql.Timestamp;
import java.util.Collection;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;
    private final CategoryValidator categoryValidator;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
        this.categoryValidator = new CategoryValidator();
    }

    public CategoryServiceImpl(CategoryRepository categoryRepo, CategoryValidator categoryValidator) {
        this.categoryRepo = categoryRepo;
        this.categoryValidator = categoryValidator;
    }

    @Override
    public void loadData() {
        categoryRepo.load();
    }

    @Override
    public void saveData() {
        categoryRepo.save();
    }

    @Override
    public Collection<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) throws NonExistingEntityException {
        var category = categoryRepo.findById(id);
        if (category == null)
            throw new NonExistingEntityException("Category with ID='" + id + "' does not exist.");

        return category;
    }

    @Override
    public Category addCategory(Category category) throws InvalidEntityDataException {
        try {
            categoryValidator.validate(category);
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityDataException(
                    String.format("Error creating category '%s'", category.getTitle()),
                    ex
            );
        }
        category.setCreated(new Timestamp(System.currentTimeMillis()));
        category.setModified(new Timestamp(System.currentTimeMillis()));
        var created = categoryRepo.create(category);
        categoryRepo.save();
        return created;
    }

    @Override
    public Category updateCategory(Category category) throws NonExistingEntityException, InvalidEntityDataException {
        try {
            categoryValidator.validate(category);
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityDataException(
                    String.format("Error creating category '%s'", category.getTitle()),
                    ex
            );
        }
        category.setModified(new Timestamp(System.currentTimeMillis()));
        var updated = categoryRepo.update(category);
        categoryRepo.save();
        return updated;
    }

    @Override
    public Category deleteCategoryById(Long id) throws NonExistingEntityException {
        var deleted = categoryRepo.deleteById(id);
        categoryRepo.save();
        return deleted;
    }

    @Override
    public long count() {
        return categoryRepo.count();
    }

    @Override
    public Category getCategoryByTitle(String title) throws NonExistingEntityException {
        var category = categoryRepo.findByTitle(title);
        if (category == null)
            throw new NonExistingEntityException("Category with Title='" + title + "' does not exist.");
        return category;
    }

    @Override
    public Category deleteCategoryByTitle(String title) throws NonExistingEntityException {
        var deleted = categoryRepo.deleteByTitle(title);
        categoryRepo.save();
        return deleted;
    }
}
