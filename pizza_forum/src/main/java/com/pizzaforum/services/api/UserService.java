package com.pizzaforum.services.api;

import com.pizzaforum.models.bindingModels.RegisterUser;
import com.pizzaforum.models.viewModels.RegisteredUserView;
import com.pizzaforum.models.viewModels.UserView;

public interface UserService {

    RegisteredUserView findByUsernameAndEmail(String username, String email);

    // Returns true if user is successfully registered, otherwise returns false
    boolean register(RegisterUser registerUser);

    RegisteredUserView findByUsernameOrEmailAndPassword(String usernameOrEmail, String password);

    UserView findAllTopicsForUser(Long userId);
}
