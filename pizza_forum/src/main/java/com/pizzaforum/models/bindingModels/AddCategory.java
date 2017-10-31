package com.pizzaforum.models.bindingModels;

import com.pizzaforum.staticData.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AddCategory {

    private String name;

    public AddCategory() {
    }

    @NotNull(message = Constants.CATEGORY_NAME_EMPTY_MESSAGE)
    @Length(min = Constants.CATEGORY_NAME_REQUIRED_LENGTH, message = Constants.CATEGORY_NAME_REQUIRED_LENGTH_MESSAGE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
