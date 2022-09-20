//package ru.kata.spring.boot_security.demo.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.User;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class UserDaoImpl implements UserDao {
//    @PersistenceContext
//    private final EntityManager entityManager;
//
//    @Autowired
//    public UserDaoImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return entityManager.createQuery("select u from User u", User.class).getResultList();
//    }
//
//    @Override
//    public void save(User user) {
//        entityManager.persist(user);
//    }
//
//    @Override
//    public User findById(int id) {
//        TypedQuery<User> query = entityManager.createQuery(
//                "select u from User u where u.id = :id", User.class);
//        query.setParameter("id", id);
//        return query.getSingleResult();
//    }
//
//    @Override
//    public void update(int id, User updateUser) {
//        User user = findById(id);
//        user.setEmail(updateUser.getEmail());
//        user.setUsername(updateUser.getUsername());
//        user.setLastname(updateUser.getLastname());
//        entityManager.merge(user);
//    }
//
//    @Override
//    public void delete(int id) {
//        User user = findById(id);
//        entityManager.remove(user);
//    }
//}
