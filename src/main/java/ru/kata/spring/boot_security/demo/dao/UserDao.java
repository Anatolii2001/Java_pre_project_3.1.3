package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> listUsers();
    void create(User user);
    void remove(User user);
    User getUserByID(long id);
    void update(User user);
    void delete(long id);
}
