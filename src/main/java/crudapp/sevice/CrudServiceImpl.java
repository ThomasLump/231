package crudapp.sevice;

import crudapp.dao.CrudDao;
import crudapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudServiceImpl implements CrudService {
    @Autowired
    private CrudDao crudDao;

    @Override
    public Iterable<User> getUsers() {
        return crudDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        crudDao.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        crudDao.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        crudDao.updateUser(user);
    }
}
