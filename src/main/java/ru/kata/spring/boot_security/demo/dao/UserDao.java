package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDao {
    User findByName(String username);
    void delete(Long id);
    void update(User user_up);
    boolean add(User user);
    List<User> listUsers();
    User findById(Long id);
}
