package ogs.dao;

import ogs.exception.NonExistingEntityException;
import ogs.model.User;

public interface UserRepository extends PersistableRepository<Long, User> {
    User findByUsername(String username) throws NonExistingEntityException;
    User deleteByUsername(String username) throws NonExistingEntityException;
    boolean containsUsername(String username);
    boolean containsEmail(String email);
}
