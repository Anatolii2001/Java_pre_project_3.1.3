package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService  extends UserDetailsService {
    void addRole(Role role);
    Role findByNameRole(String name);
    List<Role> listRoles();
    Role findByIdRole(Long id);
    List<Role> listByRole(List<String> name);
    boolean add(User user);
    List<User> listUsers();
    void delete(Long id);
    void update(User us);
    User findById(Long id);
    User findByUsername(String username);
}