package ogs.dao;

import ogs.exception.NonExistingEntityException;
import java.util.Collection;

public interface Repository<K, V extends Identifiable<K>> {
    Collection<V> findAll();

    V findById(K id);

    V create(V entity);

    void clear();

    void addAll(Collection<V> entities);

    V update(V entity) throws NonExistingEntityException;

    V deleteById(K id) throws NonExistingEntityException;

    long count();
}
