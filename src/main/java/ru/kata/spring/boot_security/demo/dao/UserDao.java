//package ru.kata.spring.boot_security.demo.dao;
//
//import ru.kata.spring.boot_security.demo.model.User;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UserDao {
//
//    static Optional<Object> findByUsername(String username) {
//        return Optional.empty();
//    }
//
//    List<User> getAllUsers();
//    void save(User user);
//    User findById(int id);
//    void update(int id, User updateUser);
//    void delete(int id);
//}