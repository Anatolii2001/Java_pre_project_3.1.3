package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public void add(User user) {
        encodePassword(user);
        setRolesForUser(user);
        userDao.create(user);
    }

    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    public User getUserByID(long id) {
        return userDao.getUserByID(id);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        User userFromDb = userDao.getUserByID(user.getId());
        if (!userFromDb.getPassword().equals(user.getPassword())) {
            encodePassword(user);
        }
        setRolesForUser(user);
        userDao.update(user);
    }

    private void setRolesForUser(User user) {
        user.setRoles(user.getRoles().stream().map(role -> roleService.findByName(role.getName())).toList());
    }

    private void encodePassword(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }
}