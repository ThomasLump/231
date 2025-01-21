package crudapp.sevice;

import crudapp.model.User;

public interface CrudService {
    Iterable<User> getUsers();
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
}
