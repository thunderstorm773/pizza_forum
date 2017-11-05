package com.pizzaforum.repositories.impl;

import com.pizzaforum.entities.User;
import com.pizzaforum.repositories.api.UserRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserRepository.class)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsernameAndEmail(String username, String email) {
        Query query = this.entityManager
                .createQuery("SELECT u FROM User AS u WHERE u.username = :username OR u.email = :email");
        query.setParameter("username", username);
        query.setParameter("email", email);
        User user = (User) query.getResultList().stream()
                .findFirst().orElse(null);
        return user;
    }

    @Override
    public void register(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public Long findUsersCount() {
       Query query = this.entityManager
               .createQuery("SELECT COUNT(u) FROM User AS u");
       Long usersCount = (Long) query.getResultList().stream()
               .findFirst().orElse(null);
       return usersCount;
    }

    @Override
    public User findByUsernameOrEmailAndPassword(String usernameOrEmail, String password) {
        Query query = this.entityManager
                .createQuery("SELECT u FROM User AS u WHERE (u.username = :username OR u.email = :email) AND u.password = :password");
        query.setParameter("username", usernameOrEmail);
        query.setParameter("email", usernameOrEmail);
        query.setParameter("password", password);
        User user = (User) query.getResultList().stream().findFirst()
                .orElse(null);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        Query query = this.entityManager
                .createQuery("SELECT u FROM User AS u WHERE u.username = :username");
        query.setParameter("username", username);
        User user = (User) query.getResultList().stream()
                .findFirst().orElse(null);
        return user;
    }

    @Override
    public User findById(Long id) {
        return this.entityManager.find(User.class, id);
    }
}
