package ogs.dao;

import ogs.exception.NonExistingEntityException;
import ogs.model.Category;
import ogs.model.User;

public interface CategoryRepository extends PersistableRepository<Long, Category> {
    Category findByTitle(String title) throws NonExistingEntityException;
    Category deleteByTitle(String title) throws NonExistingEntityException;
}
