package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleDao {
    List<Role> setRoles();
    Role findByName(String name);
    Role findById(String id);
    void createRole(Role role);
}
