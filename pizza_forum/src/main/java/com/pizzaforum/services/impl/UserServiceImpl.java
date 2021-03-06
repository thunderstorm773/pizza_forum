package com.pizzaforum.services.impl;

import com.pizzaforum.entities.Topic;
import com.pizzaforum.entities.User;
import com.pizzaforum.enums.Role;
import com.pizzaforum.models.bindingModels.RegisterUser;
import com.pizzaforum.models.viewModels.RegisteredUserView;
import com.pizzaforum.models.viewModels.TopicView;
import com.pizzaforum.models.viewModels.UserView;
import com.pizzaforum.repositories.api.UserRepository;
import com.pizzaforum.services.api.UserService;
import com.pizzaforum.utils.MapperUtil;
import org.modelmapper.PropertyMap;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisteredUserView findByUsernameAndEmail(String username, String email) {
        User user = this.userRepository.findByUsernameAndEmail(username, email);
        RegisteredUserView registeredUserView = null;
        if (user != null) {
            registeredUserView = MapperUtil.getInstance().getModelMapper()
                    .map(user, RegisteredUserView.class);
        }

        return registeredUserView;
    }

    @Override
    public boolean register(RegisterUser registerUser) {
        String username = registerUser.getUsername();
        String email = registerUser.getEmail();
        RegisteredUserView registeredUserView = this.findByUsernameAndEmail(username, email);
        if (registeredUserView != null) {
            return false;
        }

        // Default user role
        Role role = Role.USER;
        Long usersCount = this.userRepository.findUsersCount();
        if (usersCount.equals(0L)) {
            role = Role.ADMIN;
        }

        User userToRegister = MapperUtil.getInstance()
                .getModelMapper().map(registerUser, User.class);
        userToRegister.setRole(role);
        this.userRepository.register(userToRegister);
        return true;
    }

    @Override
    public RegisteredUserView findByUsernameOrEmailAndPassword(String usernameOrEmail, String password) {
        User user = this.userRepository.findByUsernameOrEmailAndPassword(usernameOrEmail, password);
        RegisteredUserView registeredUserView = null;

        if (user != null) {
            registeredUserView = MapperUtil.getInstance().getModelMapper()
                    .map(user, RegisteredUserView.class);
        }

        return registeredUserView;
    }

    @Override
    public UserView findAllTopicsForUser(Long userId) {
        User user = this.userRepository.findById(userId);
        UserView userView = null;
        if (user != null) {
            List<Topic> topics = new ArrayList<>(user.getTopics());
            PropertyMap<Topic, TopicView> propertyMap = new PropertyMap<Topic, TopicView>() {
                @Override
                protected void configure() {
                    map().setRepliesCount(source.getReplies().size());
                }
            };

            List<TopicView> topicViews = MapperUtil.getInstance()
                    .convertAll(topics, Topic.class, TopicView.class, propertyMap);
            userView = new UserView();
            String username = user.getUsername();
            userView.setUsername(username);
            userView.setTopics(topicViews);
        }

        return userView;
    }
}
