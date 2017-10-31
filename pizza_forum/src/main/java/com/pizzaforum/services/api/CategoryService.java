package com.pizzaforum.services.api;

import com.pizzaforum.models.bindingModels.AddCategory;
import com.pizzaforum.models.bindingModels.EditCategory;
import com.pizzaforum.models.viewModels.CategoryView;

import java.util.List;

public interface CategoryService {

    // Returns true if category is successfully created, otherwise returns false
    boolean create(AddCategory addCategory);

    List<CategoryView> findAll();

    CategoryView findById(Long id);

    // Returns true if category is successfully edited, otherwise returns false
    boolean edit(EditCategory editCategory);

    void deleteById(Long id);
}
