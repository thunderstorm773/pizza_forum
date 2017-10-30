package com.pizzaforum.controllers;

import com.pizzaforum.models.bindingModels.LoginUser;
import com.pizzaforum.models.bindingModels.RegisterUser;
import com.pizzaforum.models.viewModels.RegisteredUserView;
import com.pizzaforum.services.api.UserService;
import com.pizzaforum.staticData.Constants;
import com.pizzaforum.utils.ValidationUtil;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.model.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class UserController {

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/forum/register")
    public String getRegisterPage(Model model) {
        model.addAttribute(Constants.TITLE_KEY, Constants.REGISTER_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.REGISTER_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/forum/register")
    public String registerUser(@ModelAttribute RegisterUser registerUser, Model model) {
        ValidationUtil<RegisterUser> validationUtil = new ValidationUtil<>(registerUser);
        List<String> errors = validationUtil.getInvalidParamsMessages();

        if (errors.isEmpty()) {
            String password = registerUser.getPassword();
            String confirmPassword = registerUser.getConfirmPassword();
            if (!password.equals(confirmPassword)) {
                errors.add(Constants.PASSWORDS_NOT_MATCHING_MESSAGE);
            }
        }

        if (errors.isEmpty()) {
            boolean isUserSuccessfullyRegistered = this.userService.register(registerUser);
            if (!isUserSuccessfullyRegistered) {
                errors.add(Constants.USER_ALREADY_EXISTS_MESSAGE);
            }
        }

        if (!errors.isEmpty()) {
            model.addAttribute(Constants.TITLE_KEY, Constants.REGISTER_TITLE_VALUE);
            model.addAttribute(Constants.ERRORS_KEY, errors);
            model.addAttribute(Constants.VIEW_KEY, Constants.REGISTER_VIEW_VALUE);
            return "base-layout";
        }

        return "redirect:/forum/login";
    }

    @GetMapping("/forum/login")
    public String getLoginPage(Model model) {
        model.addAttribute(Constants.TITLE_KEY, Constants.LOGIN_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.LOGIN_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/forum/login")
    public String login(@ModelAttribute LoginUser loginUser,
                        HttpSession session,
                        Model model) {
        String usernameOrEmail = loginUser.getUsername();
        String password = loginUser.getPassword();
        if ((usernameOrEmail != null && !usernameOrEmail.isEmpty()) &&
                (password != null && !password.isEmpty())) {
            RegisteredUserView registeredUserView = this.userService
                    .findByUsernameOrEmailAndPassword(usernameOrEmail, password);

            if (registeredUserView == null) {
                List<String> errors = new ArrayList<>();
                errors.add(Constants.INCORRECT_USERNAME_OR_PASSWORD_MESSAGE);
                model.addAttribute(Constants.TITLE_KEY, Constants.LOGIN_TITLE_VALUE);
                model.addAttribute(Constants.ERRORS_KEY, errors);
                model.addAttribute(Constants.VIEW_KEY, Constants.LOGIN_VIEW_VALUE);
                return "base-layout";
            }

            session.setAttribute(Constants.LOGGED_IN_USER_KEY, registeredUserView);
        } else {
            return "redirect:/forum/login";
        }

        return "redirect:/forum/home";
    }

    @GetMapping("/forum/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/forum/home";
    }
}
