package ogs.service.impl;

import ogs.dao.UserRepository;
import ogs.exception.ConstraintViolationException;
import ogs.exception.InvalidEntityDataException;
import ogs.exception.NonExistingEntityException;
import ogs.model.*;
import ogs.service.UserService;
import ogs.util.UserValidator;

import java.sql.Timestamp;
import java.util.Collection;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserValidator userValidator;

    private String emailRegex = "^(.+)@(.+)$";
    private String usernameRegex = "^[A-za-z]{1,15}$";
    private String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[?!@#$%^&*()])[A-Za-z\\d?!@#$%^&*()]{8,15}$";

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
        this.userValidator = new UserValidator();
    }

    public UserServiceImpl(UserRepository userRepo, UserValidator userValidator) {
        this.userRepo = userRepo;
        this.userValidator = userValidator;
    }

    @Override
    public void loadData() {
        userRepo.load();
    }

    @Override
    public void saveData() {
        userRepo.save();
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) throws NonExistingEntityException {
        var user = userRepo.findById(id);
        if (user == null)
            throw new NonExistingEntityException("User with ID='" + id + "' does not exist.");
        return user;
    }

    @Override
    public User addUser(User user) throws InvalidEntityDataException {
        if(containsUsername(user.getUsername())){
            throw new InvalidEntityDataException(String.format("User with username: '%s' already exists.", user.getUsername()));
        }
        if (containsEmail(user.getEmail())) {
            throw new InvalidEntityDataException(String.format("User with email: '%s' already exists.", user.getEmail()));
        }
        try {
            userValidator.validate(user);
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityDataException(
                    String.format("Error creating user '%s'", user.getUsername()),
                    ex
            );
        }
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setModified(new Timestamp(System.currentTimeMillis()));
        var created = userRepo.create(user);
        userRepo.save();
        return created;
    }

    @Override
    public User updateUser(User user) throws NonExistingEntityException, InvalidEntityDataException {
        try {
            userValidator.validate(user);
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityDataException(
                    String.format("Error updating user '%s'", user.getUsername()),
                    ex
            );
        }
        user.setModified(new Timestamp(System.currentTimeMillis()));
        var updated = userRepo.update(user);
        userRepo.save();
        return updated;
    }

    @Override
    public User deleteUserById(Long id) throws NonExistingEntityException {
        var deleted = userRepo.deleteById(id);
        userRepo.save();
        return deleted;
    }

    @Override
    public long count() {
        return userRepo.count();
    }

    @Override
    public User getUserByUsername(String username) throws NonExistingEntityException {
        var user = userRepo.findByUsername(username);
        if (user == null)
            throw new NonExistingEntityException("User with Username='" + username + "' does not exist.");
        return user;
    }

    @Override
    public User deleteUserByUsername(String username) throws NonExistingEntityException {
        var deleted = userRepo.deleteByUsername(username);
        userRepo.save();
        return deleted;
    }

    @Override
    public boolean containsUsername(String username) {
        return userRepo.containsUsername(username);
    }

    @Override
    public boolean containsEmail(String email) {
        return userRepo.containsEmail(email);
    }


    @Override
    public void depositMoney(Customer customer, double money) {
        customer.setBalance(customer.getBalance() + money);
    }

    @Override
    public void buyGame(Customer customer, Game game) throws InvalidEntityDataException {
        double gamePrice = game.getPrice();
        if (customer.getBalance() > gamePrice) {
            customer.setBalance(customer.getBalance() - gamePrice);
            Seller seller = game.getSeller();
            seller.setBalance(seller.getBalance() + gamePrice);
        } else
            throw new InvalidEntityDataException("Not enough money.");
    }
}
