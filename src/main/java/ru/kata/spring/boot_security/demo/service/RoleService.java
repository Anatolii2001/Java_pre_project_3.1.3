package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesList();
    List<Role> setRoles();
    Role findByName(String name);
    Role findById(String id);
    void createRole(Role role);
    void addRole(Role role);
}
