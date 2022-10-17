package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> setRoles() {
        return new ArrayList<>(entityManager.createQuery("select r from Role r", Role.class).getResultList());
    }

    @Override
    public Role findByName(String name) {
        return entityManager.createQuery("select r from Role r where r.name = ?1",Role.class).setParameter(1,name).getSingleResult();
    }

    @Override
    public Role findById(String id) {
        return entityManager.createQuery("select r from Role r where r.id = ?1",Role.class).setParameter(1,id).getSingleResult();
    }

    @Override
    public void createRole(Role role) {
        entityManager.persist(role);
    }
}
