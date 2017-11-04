package com.pizzaforum.services.impl;

import com.pizzaforum.entities.Category;
import com.pizzaforum.entities.Topic;
import com.pizzaforum.models.bindingModels.AddCategory;
import com.pizzaforum.models.bindingModels.EditCategory;
import com.pizzaforum.models.viewModels.CategoryView;
import com.pizzaforum.models.viewModels.TopicView;
import com.pizzaforum.repositories.api.CategoryRepository;
import com.pizzaforum.services.api.CategoryService;
import com.pizzaforum.utils.MapperUtil;
import org.modelmapper.PropertyMap;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Inject
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean create(AddCategory addCategory) {
        String categoryName = addCategory.getName();
        Category createdCategory = this.categoryRepository.findByName(categoryName);
        if (createdCategory != null) {
            return false;
        }

        Category categoryToCreate = MapperUtil.getInstance().getModelMapper()
                .map(addCategory, Category.class);
        this.categoryRepository.create(categoryToCreate);
        return true;
    }

    @Override
    public List<CategoryView> findAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryView> categoryViews = MapperUtil.getInstance()
                .convertAll(categories, CategoryView.class);
        return categoryViews;
    }

    @Override
    public CategoryView findById(Long id) {
        Category category = this.categoryRepository.findById(id);
        CategoryView categoryView = null;
        if (category != null) {
            categoryView = MapperUtil.getInstance().getModelMapper()
                    .map(category, CategoryView.class);
        }

        return categoryView;
    }

    @Override
    public boolean edit(EditCategory editCategory) {
        String editCategoryName = editCategory.getName();
        Category category = this.categoryRepository.findByName(editCategoryName);
        if (category != null) {
            return false;
        }

        Category categoryToEdit = MapperUtil.getInstance().getModelMapper()
                .map(editCategory, Category.class);
        this.categoryRepository.edit(categoryToEdit);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        CategoryView categoryView = this.findById(id);
        if (categoryView == null) {
            return;
        }

        this.categoryRepository.deleteById(id);
    }

    @Override
    public List<TopicView> findAllTopicsForCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId);
        List<TopicView> topicViews = null;
        if (category != null) {
            List<Topic> topics = new ArrayList<>(category.getTopics());
            PropertyMap<Topic, TopicView> propertyMap = new PropertyMap<Topic, TopicView>() {
                @Override
                protected void configure() {
                    map().setRepliesCount(source.getReplies().size());
                }
            };

            topicViews = MapperUtil.getInstance().convertAll(topics, Topic.class,
                    TopicView.class, propertyMap);
        }

        return topicViews;
    }
}
