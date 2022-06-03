package ogs.dao.impl;

import ogs.dao.IdGenerator;
import ogs.dao.UserRepository;
import ogs.exception.NonExistingEntityException;
import ogs.model.User;

class UserRepositoryFileImpl extends PersistableRepositoryFileImpl<Long, User>
implements UserRepository {

    public UserRepositoryFileImpl(IdGenerator<Long> idGenerator, String dbFileName) {
        super(idGenerator, dbFileName);
    }


    @Override
    public boolean containsUsername(String username) {
        for (User user : getModels().values()) {
            var usernameOfUser = user.getUsername();
            if (username.equals(usernameOfUser)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsEmail(String email) {
        for (User user : getModels().values()) {
            var emailOfUser = user.getEmail();
            if (email.equals(emailOfUser)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByUsername(String username) throws NonExistingEntityException {
        for (User user : getModels().values()) {
            var usernameOfUser = user.getUsername();
            if(username.equals(usernameOfUser)){
                return user;
            }
        }
        throw new NonExistingEntityException("User with username '" + username + "' does not exist.");
    }

    @Override
    public User deleteByUsername(String username) throws NonExistingEntityException {
        var user = findByUsername(username);
        deleteById(user.getId());
        return user;
    }
}
