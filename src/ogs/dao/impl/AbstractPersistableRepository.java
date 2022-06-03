package ogs.dao.impl;

import ogs.dao.IdGenerator;
import ogs.dao.Identifiable;
import ogs.dao.PersistableRepository;
import ogs.exception.NonExistingEntityException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPersistableRepository<K,V extends Identifiable<K>>
        implements PersistableRepository<K,V> {
    private Map<K, V> models = new HashMap<>();
    private IdGenerator<K> idGenerator;

    public AbstractPersistableRepository(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void load() {
    }

    @Override
    public void save() {
    }

    @Override
    public void addAll(Collection<V> entities) {
        for (var entity : entities) {
            models.put(entity.getId(), entity);
        }
    }

    @Override
    public void clear() {
        models.clear();
    }

    @Override
    public Collection<V> findAll() {
        return models.values();
    }

    @Override
    public V findById(K id) {
        return models.get(id);
    }

    @Override
    public V create(V entity) {
        entity.setId(idGenerator.getNextId());
        models.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public V update(V entity) throws NonExistingEntityException {
        V old = findById(entity.getId());
        if (old == null)
            throw new NonExistingEntityException(entity.getClass().getName() + " with ID='" + entity.getId() + "' does not exist.");
        models.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public V deleteById(K id) throws NonExistingEntityException {
        V old = models.remove(id);
        if (old == null)
            throw new NonExistingEntityException("This id does not exist.");
        return old;
    }

    @Override
    public long count() {
        return models.size();
    }

    public IdGenerator<K> getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Map<K, V> getModels() {
        return models;
    }
}
