package crudapp.dao;

import crudapp.model.User;

public interface CrudDao {
    Iterable<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
