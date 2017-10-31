package com.pizzaforum.repositories.api;

import com.pizzaforum.entities.Category;

import java.util.List;

public interface CategoryRepository {

    void create(Category category);

    Category findByName(String categoryName);

    List<Category> findAll();

    Category findById(Long id);

    void edit(Category categoryToEdit);

    void deleteById(Long id);
}
