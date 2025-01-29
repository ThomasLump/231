package root.dao;

import root.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
