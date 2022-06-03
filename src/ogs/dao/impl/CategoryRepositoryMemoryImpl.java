package ogs.dao.impl;

import ogs.dao.CategoryRepository;
import ogs.dao.IdGenerator;
import ogs.exception.NonExistingEntityException;
import ogs.model.Category;

class CategoryRepositoryMemoryImpl extends AbstractPersistableRepository<Long, Category>
        implements CategoryRepository {

    public CategoryRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
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
