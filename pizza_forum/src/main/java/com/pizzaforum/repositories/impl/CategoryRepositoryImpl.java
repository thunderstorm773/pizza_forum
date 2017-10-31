package com.pizzaforum.repositories.impl;

import com.pizzaforum.entities.Category;
import com.pizzaforum.repositories.api.CategoryRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(CategoryRepository.class)
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Category category) {
        this.entityManager.persist(category);
    }

    @Override
    public Category findByName(String categoryName) {
        Query query = this.entityManager
                .createQuery("SELECT c FROM Category AS c WHERE c.name = :name");
        query.setParameter("name", categoryName);
        Category category = (Category) query.getResultList().stream()
                .findFirst().orElse(null);
        return category;
    }

    @Override
    public List<Category> findAll() {
        Query query = this.entityManager.createQuery("SELECT c FROM Category AS c");
        List<Category> categories = (List<Category>) query.getResultList();
        return categories;
    }

    @Override
    public Category findById(Long id) {
        return this.entityManager.find(Category.class, id);
    }

    @Override
    public void edit(Category categoryToEdit) {
        Long categoryToEditId = categoryToEdit.getId();
        Category category = this.entityManager.find(Category.class, categoryToEditId);
        String categoryToEditName = categoryToEdit.getName();
        category.setName(categoryToEditName);
    }

    @Override
    public void deleteById(Long id) {
        Category category = this.entityManager.find(Category.class, id);
        this.entityManager.remove(category);
    }
}
