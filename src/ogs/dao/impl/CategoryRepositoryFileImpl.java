package ogs.dao.impl;

import ogs.dao.CategoryRepository;
import ogs.dao.IdGenerator;
import ogs.exception.NonExistingEntityException;
import ogs.model.Category;
import ogs.model.User;

class CategoryRepositoryFileImpl extends PersistableRepositoryFileImpl<Long, Category>
        implements CategoryRepository {

    public CategoryRepositoryFileImpl(IdGenerator<Long> idGenerator, String dbFileName) {
        super(idGenerator, dbFileName);
    }

    @Override
    public Category findByTitle(String title) throws NonExistingEntityException {
        for (Category category : getModels().values()) {
            var titleOfCategory = category.getTitle();
            if(title.equals(titleOfCategory)){
                return category;
            }
        }
        throw new NonExistingEntityException("Category with title '" + title + "' does not exist.");
    }

    @Override
    public Category deleteByTitle(String title) throws NonExistingEntityException {
        var category = findByTitle(title);
        deleteById(category.getId());
        return category;
    }
}
