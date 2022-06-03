package ogs.service;

import ogs.exception.InvalidEntityDataException;
import ogs.exception.NonExistingEntityException;
import ogs.model.Customer;
import ogs.model.Game;
import ogs.model.Seller;
import ogs.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    void loadData();

    void saveData();

    Collection<User> getAllUsers();

    User getUserById(Long id) throws NonExistingEntityException;

    User addUser(User user) throws InvalidEntityDataException;

    User updateUser(User user) throws NonExistingEntityException, InvalidEntityDataException;

    User deleteUserById(Long id) throws NonExistingEntityException;

    long count();

    User getUserByUsername(String username) throws NonExistingEntityException;

    User deleteUserByUsername(String username) throws NonExistingEntityException;

    boolean containsUsername(String username);

    boolean containsEmail(String username);

    void depositMoney(Customer customer, double money);

    void buyGame(Customer customer, Game game) throws InvalidEntityDataException;
}
