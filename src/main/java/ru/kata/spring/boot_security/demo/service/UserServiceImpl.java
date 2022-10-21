package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final RoleDaoImpl roleDao;
    private final UserDaoImpl userDao;

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Autowired
    public UserServiceImpl(RoleDaoImpl roleDao, UserDaoImpl userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    public void addRole(Role role) {
        Role user_primary = roleDao.findByName(role.getRole());
        if(user_primary != null) {
            return;
        }
            roleDao.add(role);
    }

    public Role findByNameRole(String name) {
        return roleDao.findByName(name);
    }

    public List<Role> listRoles() {
        return roleDao.listRoles();
    }

    public Role findByIdRole(Long id) {
        return roleDao.findByIdRole(id);
    }

    public List<Role> listByRole(List<String> name) {
        return roleDao.listByName(name);
    }

    public boolean add(User user) {
        User user_primary = userDao.findByName(user.getUsername());
        if(user_primary != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        userDao.add(user);
        return true;
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public void update(User user_up) {
        User user_primary = findById(user_up.getId());
        System.out.println(user_primary);
        System.out.println(user_up);
        if(!user_primary.getPassword().equals(user_up.getPassword())) {
            user_up.setPassword(bCryptPasswordEncoder().encode(user_up.getPassword()));
        }
        userDao.update(user_up);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public User findByUsername(String username) {
        return userDao.findByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user_primary = findByUsername(username);
        if (user_primary == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        UserDetails user = new org.springframework.security.core.userdetails.User(user_primary.getUsername(), user_primary.getPassword(), ath(user_primary.getRoles()));
        return user_primary;
    }

    private Collection<? extends GrantedAuthority> ath(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }
}