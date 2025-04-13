package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional; // Import Transactional
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional // Add Transactional annotation
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional // Add Transactional annotation
    public void deleteUser(long id) {
        User user = getUser(id); // Get the user first
        if (user != null) {      // Check if the user exists
            entityManager.remove(user);
        }
    }

    @Override
    @Transactional // Add Transactional annotation
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}