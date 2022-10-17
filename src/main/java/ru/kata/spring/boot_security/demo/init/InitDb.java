package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitDb {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitDb(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void postConstruct() {
        List<User> users = userService.listUsers();
        if (users.isEmpty()) {
            Role adminRole = new Role("ROLE_ADMIN");
            Role userRole = new Role("ROLE_USER");
            roleService.addRole(adminRole);
            roleService.addRole(userRole);

//            List<Role> testRoles = new ArrayList<>();
//            testRoles.add(adminRole);
//            testRoles.add(userRole);
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(adminRole);
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(userRole);

//            userService.add(new User("test", "test", "test", "test@gmail.com", "test"));
            userService.add(new User("admin", "admin", "admin", "admin@gmail.com", "admin"));
            userService.add(new User("user", "user", "user", "user@gmail.com", "user"));
        }
    }
}
