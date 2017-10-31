package com.pizzaforum.staticData;

public class Constants {

    public static final String TITLE_KEY = "title";
    public static final String REGISTER_TITLE_VALUE = "Register";
    public static final String VIEW_KEY = "view";
    public static final String REGISTER_VIEW_VALUE = "/templates/user/register.jsp";
    public static final String USERNAME_EMPTY_MESSAGE = "Username cannot be empty!";
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final String USERNAME_REQUIRED_LENGTH_MESSAGE = "Username must be at least 3 symbols length!";
    public static final String USERNAME_REQUIRED_FORMAT_MESSAGE = "Username must contains only lowercase letters and numbers!";
    public static final String EMAIL_EMPTY_MESSAGE = "Email cannot be empty!";
    public static final String EMAIL_REQUIRED_FORMAT_MESSAGE = "Email must contains @!";
    public static final String PASSWORD_EMPTY_MESSAGE = "Password cannot be empty!";
    public static final String PASSWORD_REQUIRED_LENGTH_MESSAGE = "Password must be at least 4 symbols length!";
    public static final String PASSWORD_REQUIRED_FORMAT_MESSAGE = "Password must contains only digits!";
    public static final String CONFIRM_PASSWORD_EMPTY_MESSAGE = "Confirm Password cannot be empty!";
    public static final String PASSWORDS_NOT_MATCHING_MESSAGE = "Password and Confirm Password not matching!";
    public static final String ERRORS_KEY = "errors";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "User with this username or email already exists!";
    public static final String LOGIN_TITLE_VALUE = "Login";
    public static final String LOGIN_VIEW_VALUE = "templates/user/login.jsp";
    public static final String INCORRECT_USERNAME_OR_PASSWORD_MESSAGE = "Incorrect username or password!";
    public static final String LOGGED_IN_USER_KEY = "loggedInUser";
    public static final String CATEGORY_NAME_EMPTY_MESSAGE = "Category name cannot be empty!";
    public static final int CATEGORY_NAME_REQUIRED_LENGTH = 3;
    public static final String CATEGORY_NAME_REQUIRED_LENGTH_MESSAGE = "Category name must be at least 3 symbols length!";
    public static final String CREATE_CATEGORY_TITLE_VALUE = "Create category";
    public static final String CREATE_CATEGORY_VIEW_VALUE = "/templates/admin/create-category.jsp";
    public static final String CATEGORY_NAME_ALREADY_EXISTS_MESSAGE = "Category name already exists!";
    public static final String ALL_CATEGORIES_TITLE_VALUE = "All categories";
    public static final String ALL_CATEGORIES_VIEW_VALUE = "/templates/admin/all-categories.jsp";
    public static final String CATEGORIES_KEY = "categories";
    public static final String CATEGORY_KEY = "category";
    public static final String EDIT_CATEGORY_TITLE_VALUE = "Edit category";
    public static final String EDIT_CATEGORY_VIEW_VALUE = "/templates/admin/edit-category.jsp";
    public static final String DELETE_CATEGORY_TITLE_VALUE = "Delete category";
    public static final String DELETE_CATEGORY_VIEW_VALUE = "/templates/admin/delete-category.jsp";

}
