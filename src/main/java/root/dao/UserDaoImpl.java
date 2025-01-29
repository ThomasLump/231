package root.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import root.model.User;

import java.lang.reflect.Proxy;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

//    public UserDaoImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Override
    public List<User> getUsers() {
        analyzeObject(entityManager);
        return entityManager.createQuery("from User",User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        System.out.println(user);
        entityManager.persist(user);
    }

    public static void analyzeObject(Object obj) {
        Class<?> clazz = obj.getClass();

        // Суперкласс
        System.out.println("Class: " + clazz.getName());
        Class<?> superclass = clazz.getSuperclass();
        System.out.println("Superclass: " + (superclass != null ? superclass.getName() : "None"));

        // Интерфейсы
        System.out.println("Implemented interfaces:");
        for (Class<?> iface : clazz.getInterfaces()) {
            System.out.println("- " + iface.getName());
        }

        // Проверка типа прокси
        if (Proxy.isProxyClass(clazz)) {
            System.out.println("This is a JDK dynamic proxy.");
        } else if (clazz.getName().contains("$$EnhancerBySpringCGLIB$$")) {
            System.out.println("This is a CGLIB proxy.");
        } else {
            System.out.println("This is not a proxy.");
        }
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }
}
