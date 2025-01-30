package root.service;

import root.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(long id);
 }
