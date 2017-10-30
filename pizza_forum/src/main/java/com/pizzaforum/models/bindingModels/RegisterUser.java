package com.pizzaforum.models.bindingModels;

import com.pizzaforum.staticData.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegisterUser {

    private String username;

    private String email;

    private String password;

    private String confirmPassword;

    public RegisterUser() {
    }

    @NotNull(message = Constants.USERNAME_EMPTY_MESSAGE)
    @Length(min = Constants.USERNAME_MIN_LENGTH, message = Constants.USERNAME_REQUIRED_LENGTH_MESSAGE)
    @Pattern(regexp = "^[a-z0-9]+$", message = Constants.USERNAME_REQUIRED_FORMAT_MESSAGE)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = Constants.EMAIL_EMPTY_MESSAGE)
    @Pattern(regexp = ".*@.*", message = Constants.EMAIL_REQUIRED_FORMAT_MESSAGE)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = Constants.PASSWORD_EMPTY_MESSAGE)
    @Length(min = Constants.PASSWORD_MIN_LENGTH, message = Constants.PASSWORD_REQUIRED_LENGTH_MESSAGE)
    @Pattern(regexp = "^[0-9]+$", message = Constants.PASSWORD_REQUIRED_FORMAT_MESSAGE)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = Constants.CONFIRM_PASSWORD_EMPTY_MESSAGE)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
