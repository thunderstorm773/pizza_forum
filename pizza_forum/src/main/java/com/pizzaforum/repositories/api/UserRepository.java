package com.pizzaforum.repositories.api;

import com.pizzaforum.entities.User;

public interface UserRepository {

    User findByUsernameAndEmail(String username, String email);

    void register(User user);

    Long findUsersCount();

    User findByUsernameOrEmailAndPassword(String usernameOrEmail, String password);
}
