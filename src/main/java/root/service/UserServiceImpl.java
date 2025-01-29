package root.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.dao.UserDao;
import root.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        dao.deleteUser(user);
    }
}
